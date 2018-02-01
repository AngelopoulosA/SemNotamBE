package app.Repository;

import app.Model.ContextDB;
import app.Model.Flora2.Context;
import app.Model.Flora2.Parameter;
import app.Model.Flora2.ParameterValue;
import app.Model.Flora2.Rule;
import app.Model.InvalidOperationException;
import app.Startup;
import dke.pr.cli.CBRInterface;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Flora2Repository extends CBRInterface implements AutoCloseable  {

    /**
     * Creates a new Flora2Repository and initiates it with the "current" transaction CBRM files
     * Flora2Path is taken from application.properties via Startup Component
     * @throws IOException
     */
    public Flora2Repository() throws IOException {
        super();
        initiate(Startup.flora2Path,"CBRM/current/ctxModelAIM.flr", "CBRM/current/bc.flr", "AIMCtx", "SemNOTAMCase");
        setDebug(false);
    }

    /**
     * Gets the file path of a Context for Flora2
     * @param context
     * @return
     */
    public String getCtxFileName(String context) {
        File cbrmCurrent = new File("CBRM/current/Contexts");
        String path = cbrmCurrent.getAbsolutePath().replace('\\', '/');
        return path+"/"+context+".flr";
    }

    /**
     * Gets the Details of a certain Context within the Hierarchy
     * This Root Context contains the whole Contaxt Hierarchy throught its Parents and Children.
     * Parameter Values and RuleDevelopers are set for every Context.
     * @param contextDBRepository
     * @param id
     * @return
     * @throws IOException
     */
    public Context getContext(ContextDBRepository contextDBRepository, String id) throws IOException {
        Context context = null;

        List<String[]> rawHierarchy = getCtxHierarchy();

        if (rawHierarchy.size() > 0) {
            Map<String, Context> contexts = getContextHierarchyMap(rawHierarchy);

            for (Context c : contexts.values()) {
                ContextDB contextDB = contextDBRepository.findOne(c.getName());
                if(contextDB != null) {
                    c.setRuleDevelopers(contextDB.getRuleDevelopers());
                }
            }
            context = contexts.values().stream().filter(pv -> pv.getName().equals(id)).findFirst().orElseGet(null);

        } else { // No Hierarchy - just one Context
            List<String> ctxs = getCtxs();
            if(ctxs.contains(id)) {
                context = new Context(id);
            }
            ContextDB contextDB = contextDBRepository.findOne(id);
            if(contextDB != null) {
                context.setRuleDevelopers(contextDB.getRuleDevelopers());
            }
        }

        if(context == null) {
            throw new InvalidOperationException();
        }

        List<String[]> ctxInfo = getCtxInfo(context.getName());
        for (String[] parameterValue : ctxInfo) {
            context.getParameterValues().put(parameterValue[0], parameterValue[1]);
        }


        context.setRules(getRuleObjects(id));
        return context;
    }

    /**
     * Gets all rules for a Context from Flora2 and converts them to Rule Objects
     * @param id ContextId
     * @return
     * @throws IOException
     */
    public List<Rule> getRuleObjects(String id) throws IOException {
        HashMap<String, String> rawRules = getRules(id);
        List<Rule> rules = new LinkedList<>();
        for (Map.Entry<String, String> rule : rawRules.entrySet()) {
            rules.add(new Rule(rule.getKey(), rule.getValue().replace("@!{"+rule.getKey()+"}\r\n", "")));
        }
        return rules;
    }

    /**
     * Copies the CBRM/current folder to CBRM/copy
     * @throws Exception
     */
    public void startTransaction() throws IOException {
        File cbrmCurrent = new File("CBRM/current");
        File cbrmCopy = new File("CBRM/copy");
        if(cbrmCopy.exists()) {
            throw new IOException("Transaction Copy already exists");
        }
        FileUtils.forceMkdir(cbrmCopy);
        FileUtils.copyDirectory(cbrmCurrent, cbrmCopy);
    }

    /**
     * Copies the CBRM/copy folder back to CBRM/current
     * @throws Exception
     */
    public static void rollbackTransaction() throws IOException {
        File cbrmCurrent = new File("CBRM/current");
        File cbrmCopy = new File("CBRM/copy");
        if(!cbrmCopy.exists()) {
            throw new IOException("Transaction Copy does not exist");
        }
        if(cbrmCurrent.exists()) {
            FileUtils.forceDelete(cbrmCurrent);
        }
        FileUtils.forceMkdir(cbrmCurrent);
        FileUtils.copyDirectory(cbrmCopy, cbrmCurrent);
        FileUtils.forceDelete(cbrmCopy);
    }

    /**
     * Deletes the CBRM/copy folder
     * @throws Exception
     */
    public void commitTransaction() throws Exception {
        File cbrmCopy = new File("CBRM/copy");
        if(!cbrmCopy.exists()) {
            throw new Exception("Transaction Copy does not exist");
        }
        FileUtils.forceDelete(cbrmCopy);
    }

    /**
     * Gets the Root Context of the Context Hierarchy.
     * This Root Context contains the whole Contaxt Hierarchy as Children.
     * Parameter Values are set for every Context.
     * RuleDevelopers are NOT set.
     * @param contextDBRepository
     * @return
     * @throws IOException
     */
    public Context getRootContext(ContextDBRepository contextDBRepository) throws IOException {
        List<String[]> rawHierarchy = getCtxHierarchy();

        if(rawHierarchy.size() > 0) {
            Map<String, Context> contexts = getContextHierarchyMap(rawHierarchy);

            for (Context context : contexts.values()) {
                List<String[]> ctxInfo = getCtxInfo(context.getName());
                for (String[] parameterValue : ctxInfo) {
                    context.getParameterValues().put(parameterValue[0], parameterValue[1]);
                }
            }
            Context root = contexts.values().stream().filter(pv -> pv.getParents().isEmpty()).findFirst().get();
            return root;
        }
        return null;
    }

    /**
     * Creates the ContextHierarchy Tree but does return every Context in the Map instead of only the Root Context
     * @param rawHierarchy
     * @return
     */
    private Map<String, Context> getContextHierarchyMap(List<String[]> rawHierarchy){
        Map<String, Context> contexts = new Hashtable<>();

        for (String[] hierarchy : rawHierarchy) {
            Context parent = contexts.get(hierarchy[1]);
            if (parent == null) {
                parent = new Context(hierarchy[1]);
                contexts.put(parent.getName(), parent);
            }
            Context child = contexts.get(hierarchy[0]);
            if (child == null) {
                child = new Context(hierarchy[0]);
                contexts.put(child.getName(), child);
            }
            parent.getChildren().add(child);
            child.getParents().add(parent);
        }
        return contexts;
    }

    /**
     * Gets the Details of a Parameter with DetParamValue and ParameterValue Hierarchy
     * @param id
     * @return
     * @throws IOException
     */
    public Parameter getParameterDetails(String id) throws IOException {
        List<String[]> rawParamValuesHierarchy = getParameterValuesHiearchy(id);

        Parameter parameter = new Parameter(id);
        if (rawParamValuesHierarchy.size() > 0) {
            Map<String, ParameterValue> parameterValues = new HashMap<>();
            for (String[] hierarchy : rawParamValuesHierarchy) {
                ParameterValue parent = parameterValues.get(hierarchy[0]);
                if (parent == null) {
                    parent = new ParameterValue(hierarchy[0]);
                    parameterValues.put(parent.getName(), parent);
                }
                ParameterValue child = parameterValues.get(hierarchy[1]);
                if (child == null) {
                    child = new ParameterValue(hierarchy[1]);
                    parameterValues.put(child.getName(), child);
                }
                parent.getChildren().add(child);
                child.getParents().add(parent);
            }
            ParameterValue root = parameterValues.values().stream().filter(pv -> pv.getParents().isEmpty()).findFirst().get();
            parameter.setParameterValueHierarchy(root);
        } else {
            List<String> parameterValues = getParameterParameterValues(id);
            if (parameterValues.size() == 1) { // Parameter has only one value and thus no hierarchy
                parameter.setParameterValueHierarchy(new ParameterValue(parameterValues.get(0)));
            } else {
                return null;
            }
        }

        parameter.setDetParamValue(getDetParamValue(id));
        return parameter;
    }

    /**
     * Checks whether a certain Context with the same ParameterValue already exists in the Hierarchy
     * @param context
     * @return
     * @throws IOException
     */
    public boolean contextWithParameterValuesExits(Context context) throws IOException {
        List<String> contexts = getCtxs();
        for (String contextName : contexts) {
            List<String[]> ctxInfo = getCtxInfo(contextName);
            boolean allMatch = true;
            for (String[] parameterValue : ctxInfo) {
                allMatch = allMatch && context.getParameterValues().get(parameterValue[0]).equals(parameterValue[1]);
            }
            if (allMatch) {
                return true;
            }
        }
        return false;
    }
}
