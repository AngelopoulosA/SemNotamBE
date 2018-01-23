package app.Repository;

import app.Model.ContextDB;
import app.Model.Flora2.Context;
import app.Model.Flora2.Rule;
import app.Model.InvalidOperationException;
import app.Startup;
import dke.pr.cli.CBRInterface;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Flora2Repository extends CBRInterface implements AutoCloseable  {

    public Flora2Repository() throws IOException {
        super();
        initiate(Startup.flora2Path,"CBRM/current/ctxModelAIM.flr", "CBRM/current/bc.flr", "AIMCtx", "SemNOTAMCase");
        setDebug(false);
    }

    public String getCtxFileName(String context) {
        File cbrmCurrent = new File("CBRM/current");
        String path = cbrmCurrent.getAbsolutePath().replace('\\', '/');
        return path+"/"+context+".flr";
    }


    public Context getContext(ContextDBRepository contextDBRepository, String id) throws Exception {
        Context context = null;

        List<String[]> rawHierarchy = getCtxHierarchy();

        if (rawHierarchy.size() > 0) {
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

            context = contexts.values().stream().filter(pv -> pv.getName().equals(id)).findFirst().orElseGet(null);

        } else { // No Hierarchy - just one Context
            List<String> ctxs = getCtxs();
            if(ctxs.contains(id)) {
                context = new Context(id);
            }
        }

        if(context == null) {
            throw new InvalidOperationException();
        }

        List<String[]> ctxInfo = getCtxInfo(context.getName());
        for (String[] parameterValue : ctxInfo) {
            context.getParameterValues().put(parameterValue[0], parameterValue[1]);
        }

        ContextDB contextDB = contextDBRepository.findOne(id);
        if(contextDB != null) {
            context.setRuleDevelopers(contextDB.getRuleDevelopers());
        }
        context.setRules(getRuleObjects(id));
        return context;
    }

    public List<Rule> getRuleObjects(String id) throws Exception {
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
    public void startTransaction() throws Exception {
        File cbrmCurrent = new File("CBRM/current");
        File cbrmCopy = new File("CBRM/copy");
        if(cbrmCopy.exists()) {
            throw new Exception("Transaction Copy already exists");
        }
        FileUtils.forceMkdir(cbrmCopy);
        FileUtils.copyDirectory(cbrmCurrent, cbrmCopy);
    }

    /**
     * Copies the CBRM/copy folder back to CBRM/current
     * @throws Exception
     */
    public void rollbackTransaction() throws Exception {
        File cbrmCurrent = new File("CBRM/current");
        File cbrmCopy = new File("CBRM/copy");
        if(!cbrmCopy.exists()) {
            throw new Exception("Transaction Copy does not exist");
        }
        if(cbrmCurrent.exists()) {
            FileUtils.forceDelete(cbrmCurrent);
        }
        FileUtils.forceMkdir(cbrmCurrent);
        FileUtils.copyDirectory(cbrmCopy, cbrmCurrent);
        FileUtils.forceDelete(cbrmCopy);
        restart();
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
}
