package app.Controller;

import app.Model.Parameter;
import dke.pr.cli.CBRInterface;
import app.Model.ParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

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
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);
            return fl.getParameters();
        } catch (IOException e) {
            return null;
        }
	}

    @GetMapping(path="/{id}")
    public @ResponseBody
    Parameter getParameterDetails (@PathVariable(value="id") String id) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);
            List<String[]> rawParamValuesHierarchy = fl.getParameterValuesHiearchy(id);

            Parameter parameter = new Parameter(id);
            if(rawParamValuesHierarchy.size() > 0) {
                Map<String, ParameterValue> parameterValues = new HashMap<>();
                for (String[] hierarchy : rawParamValuesHierarchy) {
                    ParameterValue parent = parameterValues.get(hierarchy[0]);
                    if(parent == null) {
                        parent = new ParameterValue(hierarchy[0]);
                        parameterValues.put(parent.getName(), parent);
                    }
                    ParameterValue child = parameterValues.get(hierarchy[1]);
                    if(child == null) {
                        child = new ParameterValue(hierarchy[1]);
                        parameterValues.put(child.getName(), child);
                    }
                    parent.getChildren().add(child);
                    child.getParents().add(parent);
                }
                ParameterValue root = parameterValues.values().stream().filter(pv -> pv.getParents().isEmpty()).findFirst().get();

                parameter.setParameterValueHierarchy(root);
                parameter.setDetParamValue(fl.getDetParamValue(id));
                return parameter;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @GetMapping(path="/withValues")
    public @ResponseBody
    Map<String,List<String>> getParameterWithValues () {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            Map<String,List<String>> parametersWithValues = new HashMap<>();
            List<String> parameters = fl.getParameters();
            for (String parameter : parameters) {
                List<String> parameterValues = fl.getParameterParameterValues(parameter);
                parametersWithValues.put(parameter, parameterValues);
            }
            return parametersWithValues;

        } catch (IOException e) {
            return null;
        }
    }
}
