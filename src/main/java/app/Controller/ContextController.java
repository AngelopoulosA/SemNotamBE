package app.Controller;

import dke.pr.cli.CBRInterface;
import app.Model.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

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
            HashMap<String, String> rules = fl.getRules(id);
            context.setRules(rules);
            return context;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
