package app.Controller;

import app.Model.ComposedOperation;
import app.Model.FatalFlora2Error;
import app.Model.Flora2.Parameter;
import app.Model.Operation;
import app.Model.Operations.*;
import app.Repository.ComposedOperationLogic;
import app.Repository.Flora2Repository;
import app.Model.Flora2.ParameterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/parameters")
@CrossOrigin
public class ParameterController {
    private final ComposedOperationLogic composedOperationLogic;

    @Autowired
    public ParameterController(ComposedOperationLogic composedOperationLogic) {
        this.composedOperationLogic = composedOperationLogic;
    }


	@GetMapping(path="")
	public @ResponseBody
    List<Parameter> getAllParameters () {
        try (Flora2Repository fl = new Flora2Repository()) {
            return fl.getParameters().stream().map(p -> new Parameter(p)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
	}

    @GetMapping(path="/{id}")
    public @ResponseBody
    Parameter getParameterDetails (@PathVariable(value="id") String id) {
        try (Flora2Repository fl = new Flora2Repository()) {
            return fl.getParameterDetails(id);

        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @GetMapping(path="/withValues")
    public @ResponseBody
    List<Parameter> getParameterWithValues () {
        try (Flora2Repository fl = new Flora2Repository()) {

            List<Parameter> parametersWithValues = new LinkedList<>();
            List<String> parameters = fl.getParameters();
            for (String paramName : parameters) {
                List<String> parameterValues = fl.getParameterParameterValues(paramName);
                Parameter parameter = new Parameter(paramName);
                parameter.setParameterValues(parameterValues.stream().map(pv -> new ParameterValue(pv)).collect(Collectors.toList()));
                parametersWithValues.add(parameter);
            }
            return parametersWithValues;

        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PostMapping(path="")
    public @ResponseBody
    Parameter addParameter (@RequestBody Parameter parameter, @RequestHeader("User") Long user) {
        ComposedOperation operation = null;
        try (Flora2Repository fl = new Flora2Repository()) {
            operation = new AddParameter(parameter);
            composedOperationLogic.checkAndStartOperation(fl, operation, user);
            String detParamValueDef = parameter.getDetParamValue().stream().collect(Collectors.joining("\r\n"));
            fl.addParameter(parameter.getName(), parameter.getParameterValueHierarchy().getName(), detParamValueDef);

            Thread.sleep(1000);
            fl.restart();
            return getParameterDetails(parameter.getName());
        } catch (Exception e) {
            composedOperationLogic.handleFatalError(operation);
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    boolean deleteParameter (@PathVariable(value="id") String id, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new DeleteParameter(fl.getParameterDetails(id)), user)) {
                boolean result = fl.delParameter(id);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @PutMapping(path="/{id}")
    public @ResponseBody
    Parameter updateParameterDetParamValue (@PathVariable(value="id") String id, @RequestBody Parameter parameter, @RequestHeader("User") Long user) {
        ComposedOperation operation = null;
        try (Flora2Repository fl = new Flora2Repository()) {
            operation = new UpdateParameter(parameter);
            if (composedOperationLogic.checkAndStartOperation(fl, operation, user)) {
                String detParamValueDef = parameter.getDetParamValue().stream().collect(Collectors.joining("\r\n"));
                fl.updateDetParamValue(id, detParamValueDef);

                Thread.sleep(1000);
                fl.restart();
                return getParameterDetails(parameter.getName());
            }
        } catch (Exception e) {
            composedOperationLogic.handleFatalError(operation);
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
        return null;
    }

    @PostMapping(path="/{id}")
    public @ResponseBody
    Parameter addParameterValue (@PathVariable(value="id") String parameterId, @RequestBody ParameterValue parameterValue, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new AddParameterValue(parameterId, parameterValue), user)) {
                String[] parents = parameterValue.getParents().stream().map(p -> p.getName()).toArray(String[]::new);
                boolean result = fl.addParameterValue(parameterId, parameterValue.getName(), parents, null);
                if(result) {
                    return getParameterDetails(parameterId);
                }
            } else {
                return getParameterDetails(parameterId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping(path="/{id}/{valueId}")
    public @ResponseBody
    Parameter deleteParameterValue (@PathVariable(value="id") String parameterId, @PathVariable(value="valueId") String parameterValueId, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new DeleteParameterValue(parameterId, parameterValueId), user)) {
                boolean result = fl.delParameterValue(parameterValueId);
                if(result) {
                    return getParameterDetails(parameterId);
                }
            } else {
                return getParameterDetails(parameterId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
