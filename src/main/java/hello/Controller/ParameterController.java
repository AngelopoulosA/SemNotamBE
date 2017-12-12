package hello.Controller;

import dke.pr.cli.CBRInterface;
import hello.Model.ParameterValue;
import hello.Model.TempParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/parameters")
public class ParameterController {

	@Autowired
	public ParameterController() {
    };

	@GetMapping(path="")
	public @ResponseBody
    List<String> getAllParameters () {
        try {
            CBRInterface fl = new CBRInterface("C:/TEMP/dke/flora2/CBRM/ctxModelAIM.flr",
                    "C:/TEMP/dke/flora2/CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);
            return fl.getParameters();
        } catch (IOException e) {
            return null;
        }
	}

    @GetMapping(path="/{id}")
    public @ResponseBody
    ParameterValue getParameterDetails (@PathVariable(value="id") String id) {
        try {
            CBRInterface fl = new CBRInterface("C:/TEMP/dke/flora2/CBRM/ctxModelAIM.flr",
                    "C:/TEMP/dke/flora2/CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);
            List<String[]> rawParamValuesHierarchy = fl.getParameterValuesHiearchy(id);

            if(rawParamValuesHierarchy.size() > 0) {
                Dictionary<String, TempParameterValue> parameterValues = new Hashtable<>();
                for (String[] hierarchy : rawParamValuesHierarchy) {
                    TempParameterValue parent = parameterValues.get(hierarchy[0]);
                    if(parent == null) {
                        parent = new TempParameterValue(hierarchy[0]);
                        parameterValues.put(parent.getName(), parent);
                    }
                    TempParameterValue child = parameterValues.get(hierarchy[1]);
                    if(child == null) {
                        child = new TempParameterValue(hierarchy[1]);
                        parameterValues.put(child.getName(), child);
                    }
                    parent.getChildren().add(child);
                    child.getParents().add(parent);
                }
                Collections.list(parameterValues.elements()).stream().forEach(pv -> {
                    TempParameterValue parent = pv.getParents().stream().min(Comparator.comparingInt(p -> p.getChildren().size())).orElseGet(()->null);
                    pv.setParents(new LinkedList<>());
                    if (parent != null) {
                        pv.getParents().add(parent);
                    }
                });
                TempParameterValue tempRoot = Collections.list(parameterValues.elements()).stream().filter(pv -> pv.getParents().isEmpty()).findFirst().get();
                ParameterValue root = tempRoot.toParameterValue();
                return root;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
