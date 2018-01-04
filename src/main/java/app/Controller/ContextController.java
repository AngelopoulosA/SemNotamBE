package app.Controller;

import app.Model.Rule;
import dke.pr.cli.CBRInterface;
import app.Model.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/contexts")
public class ContextController {

	@Autowired
	public ContextController() {
    };

	@GetMapping(path="")
	public @ResponseBody
    Context getAllContexts () {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            List<String[]> rawHierarchy = fl.getCtxHierarchy();

            if(rawHierarchy.size() > 0) {
                Map<String, Context> contexts = new Hashtable<>();

                for (String[] hierarchy : rawHierarchy) {
                    Context parent = contexts.get(hierarchy[1]);
                    if(parent == null) {
                        parent = new Context(hierarchy[1]);
                        contexts.put(parent.getName(), parent);
                    }
                    Context child = contexts.get(hierarchy[0]);
                    if(child == null) {
                        child = new Context(hierarchy[0]);
                        contexts.put(child.getName(), child);
                    }
                    parent.getChildren().add(child);
                    child.getParents().add(parent);
                }

                for (Context context : contexts.values()) {
                    List<String[]> ctxInfo = fl.getCtxInfo(context.getName());
                    for (String[] parameterValue : ctxInfo) {
                        context.getParameterValues().put(parameterValue[0], parameterValue[1]);
                    }
                }
                Context root = contexts.values().stream().filter(pv -> pv.getParents().isEmpty()).findFirst().get();
                return root;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
	}

    @GetMapping(path="/{id}")
    public @ResponseBody
    Context getContextDetails (@PathVariable(value="id") String id) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            Context context = new Context(id);
            List<String[]> ctxInfo = fl.getCtxInfo(context.getName());
            for (String[] parameterValue : ctxInfo) {
                context.getParameterValues().put(parameterValue[0], parameterValue[1]);
            }

            context.setRules(getRules(fl, id));
            return context;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getRules(CBRInterface fl, String id) throws Exception {
        HashMap<String, String> rules = fl.getRules(id);
        for (Map.Entry<String, String> rule : rules.entrySet()) {
            rules.replace(rule.getKey(), rule.getValue().replace("@!{"+rule.getKey()+"}\r\n", ""));
        }
        return rules;
    }

    @PostMapping(path="/{id}/rule")
    public @ResponseBody
    Map<String, String> addRule (@PathVariable(value="id") String id, @RequestBody Rule rule) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            boolean result = fl.addRule(id, "@!{"+rule.getId()+"}\r\n"+rule.getBody());
            if(result) {
                return getRules(fl, id);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(path="/{id}/rule/{ruleId}")
    public @ResponseBody
    Map<String, String> deleteRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            boolean result = fl.delRule(id, ruleId);
            return getRules(fl, id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping(path="/{id}/rule/{ruleId}")
    public @ResponseBody
    Map<String, String> addOrUpdateRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId, @RequestBody Rule rule) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            deleteRule(id, ruleId);
            return addRule(id, rule);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path="")
    public @ResponseBody
    Context addContext (@RequestBody Context context) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            String ctxFile = "C:/TEMP/dke/SemNotamBE/CBRM/Contexts/"+context.getName()+".flr"; //TODO: Path
            String paramValues = context.getParameterValues().entrySet().stream().map(e -> e.getKey()+"->"+e.getValue()).collect(Collectors.joining(","));
            String ctxDefinition = context.getName()+":AIMCtx["+paramValues+",file->'"+ctxFile+"'].";
            boolean result = fl.addCtx(ctxDefinition, ctxFile);
            if(result) {
                return getContextDetails(context.getName());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    boolean deleteContext (@PathVariable(value="id") String id) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            boolean result = fl.delCtx(id, true);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
