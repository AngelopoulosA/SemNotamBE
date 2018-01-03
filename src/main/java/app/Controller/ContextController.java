package app.Controller;

import dke.pr.cli.CBRInterface;
import app.Model.Context;
import app.Model.TempContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                Dictionary<String, TempContext> contexts = new Hashtable<>();

                for (String[] hierarchy : rawHierarchy) {
                    TempContext parent = contexts.get(hierarchy[1]);
                    if(parent == null) {
                        parent = new TempContext(hierarchy[1]);
                        contexts.put(parent.getName(), parent);
                    }
                    TempContext child = contexts.get(hierarchy[0]);
                    if(child == null) {
                        child = new TempContext(hierarchy[0]);
                        contexts.put(child.getName(), child);
                    }
                    parent.getChildren().add(child);
                    child.getParents().add(parent);
                }
                TempContext tempRoot = Collections.list(contexts.elements()).stream().filter(pv -> pv.getParents().isEmpty()).findFirst().get();
                Context root = tempRoot.toContext();
                return root;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
	}

    @GetMapping(path="/{id}")
    public @ResponseBody
    HashMap<String, String> getContextDetails (@PathVariable(value="id") String id) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            HashMap<String, String> rules = fl.getRules(id);
            return rules;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
