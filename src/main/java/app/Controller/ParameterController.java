package app.Controller;

import app.Model.Context;
import app.Model.Parameter;
import dke.pr.cli.CBRInterface;
import app.Model.ParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            } else {
                List<String> parameterValues = fl.getParameterParameterValues(id);
                if(parameterValues.size() == 1) { // Parameter has only one value and thus no hierarchy
                    parameter.setParameterValueHierarchy(new ParameterValue(parameterValues.get(0)));
                } else {
                    return null;
                }
            }

            parameter.setDetParamValue(fl.getDetParamValue(id));
            return parameter;
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

    @PostMapping(path="")
    public @ResponseBody
    Parameter addParameter (@RequestBody Parameter parameter) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            String detParamValueDef = parameter.getDetParamValue().stream().collect(Collectors.joining("\r\n"));
            fl.addParameter(parameter.getName(), parameter.getParameterValueHierarchy().getName(), detParamValueDef);

            Thread.sleep(1000);
            fl.restart();
            return getParameterDetails(parameter.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    boolean deleteParameter (@PathVariable(value="id") String id) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            boolean result = fl.delParameter(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PutMapping(path="/{id}")
    public @ResponseBody
    Parameter updateParameterDetParamValue (@PathVariable(value="id") String id, @RequestBody Parameter parameter) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            String detParamValueDef = parameter.getDetParamValue().stream().collect(Collectors.joining("\r\n"));
            fl.updateDetParamValue(id, detParamValueDef);

            Thread.sleep(1000);
            fl.restart();
            return getParameterDetails(parameter.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path="/{id}")
    public @ResponseBody
    boolean addParameterValue (@PathVariable(value="id") String parameterId, @RequestBody ParameterValue parameterValue) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            String[] parents = parameterValue.getParents().stream().map(p -> p.getName()).toArray(String[]::new);
            boolean result = fl.addParameterValue(parameterId, parameterValue.getName(), parents, null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @DeleteMapping(path="/{id}/{valueId}")
    public @ResponseBody
    boolean deleteParameterValue (@PathVariable(value="id") String parameterId, @PathVariable(value="valueId") String parameterValueId) {
        try {
            CBRInterface fl = new CBRInterface("CBRM/ctxModelAIM.flr",
                    "CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");
            fl.setDebug(false);

            return fl.delParameterValue(parameterValueId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
