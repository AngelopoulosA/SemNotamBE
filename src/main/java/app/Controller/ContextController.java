package app.Controller;

import app.Model.ContextDB;
import app.Model.FatalFlora2Error;
import app.Model.Flora2.Rule;
import app.Model.InvalidOperationException;
import app.Model.Operations.*;
import app.Model.User;
import app.Repository.ComposedOperationLogic;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;
import app.Repository.UserRepository;
import app.Model.Flora2.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/contexts")
@CrossOrigin
public class ContextController {

    private final ContextDBRepository contextDBRepository;
    private final UserRepository userRepository;
    private final ComposedOperationLogic composedOperationLogic;


    @Autowired
    public ContextController(ContextDBRepository contextDBRepository, UserRepository userRepository, ComposedOperationLogic composedOperationLogic) {
        this.contextDBRepository = contextDBRepository;
        this.userRepository = userRepository;
        this.composedOperationLogic = composedOperationLogic;
    }

    @GetMapping(path="")
    public @ResponseBody
    List<String> getAllContextsFlat () {
        try (Flora2Repository fl = new Flora2Repository()) {
            return fl.getCtxs();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @GetMapping(path="/{id}/parents")
    public @ResponseBody
    List<String> getAllParentContextsFlat (@PathVariable(value="id") String id) {
        Context root = getContextHierarchy();
        List<Context> parentsFlat = root.findContext(id).getParentsFlat();
        return parentsFlat.stream().map(c -> c.getName()).collect(Collectors.toList());
    }

    @GetMapping(path="/{id}/children")
    public @ResponseBody
    List<String> getAllChildContextsFlat (@PathVariable(value="id") String id) {
        Context root = getContextHierarchy();
        List<Context> childrenFlat = root.findContext(id).getChildrenFlat();
        return childrenFlat.stream().map(c -> c.getName()).collect(Collectors.toList());
    }


    @GetMapping(path="/hierarchy")
    public @ResponseBody
    Context getContextHierarchy () {
        try (Flora2Repository fl = new Flora2Repository()) {
            return fl.getRootContext(contextDBRepository);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    Context getContextDetails (@PathVariable(value="id") String id) {
        try (Flora2Repository fl = new Flora2Repository()) {

            Context context = new Context(id);
            List<String[]> ctxInfo = fl.getCtxInfo(context.getName());
            for (String[] parameterValue : ctxInfo) {
                context.getParameterValues().put(parameterValue[0], parameterValue[1]);
            }

            ContextDB contextDB = contextDBRepository.findOne(id);
            if(contextDB != null) {
                context.setRuleDevelopers(contextDB.getRuleDevelopers());
            }
            context.setRules(fl.getRuleObjects(id));
            return context;

        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PostMapping(path="/{id}/rule")
    public @ResponseBody
    Context addRule (@PathVariable(value="id") String id, @RequestBody Rule rule, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            composedOperationLogic.checkAndStartOperation(fl, new AddRule(fl.getContext(contextDBRepository, id), rule), user);
            boolean result = fl.addRule(id, "@!{"+rule.getId()+"}\r\n"+rule.getBody());
            if(result) {
                return getContextDetails(id);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @DeleteMapping(path="/{id}/rule/{ruleId}")
    public @ResponseBody
    Context deleteRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new DeleteRule(fl.getContext(contextDBRepository, id), ruleId), user)) {
                boolean result = fl.delRule(id, ruleId);
                return getContextDetails(id);
            }
            return getContextDetails(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PutMapping(path="/{id}/rule/{ruleId}")
    public @ResponseBody
    Context updateRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId, @RequestBody Rule rule, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            composedOperationLogic.checkAndStartOperation(fl, new EditRule(fl.getContext(contextDBRepository, id), rule), user);
            boolean result = fl.delRule(id, ruleId);
            if (result) {
                result = fl.addRule(id, "@!{"+rule.getId()+"}\r\n"+rule.getBody());
                if (result) {
                    return getContextDetails(id);
                }
            }
            return getContextDetails(id); //TODO Exception

        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PostMapping(path="")
    public @ResponseBody
    Context addContext (@RequestBody Context context, @RequestHeader("User") Long user) throws Exception {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (fl.contextWithParameterValuesExits(context)) {
                throw new InvalidOperationException("Context with same values already exists");
            }

            composedOperationLogic.checkAndStartOperation(fl, new AddContext(context), user);
            String ctxFile = fl.getCtxFileName(context.getName());
            String paramValues = context.getParameterValues().entrySet().stream().map(e -> e.getKey() + "->" + e.getValue()).collect(Collectors.joining(","));
            String ctxDefinition = context.getName() + ":AIMCtx[" + paramValues + ",file->'" + ctxFile + "'].";
            boolean result = fl.addCtx(ctxDefinition, ctxFile);

            Thread.sleep(1000);
            fl.restart();

            if (result) {
                ContextDB contextDB = new ContextDB(context.getName(), context.getRuleDevelopers());
                contextDBRepository.save(contextDB);
                Thread.sleep(1000);
                return getContextHierarchy();
            }

            throw new InvalidOperationException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody
    boolean deleteContext (@PathVariable(value="id") String id, @RequestHeader("User") Long user) throws InterruptedException {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (fl.getRootContext(contextDBRepository).getName().equals(id)) {
                throw new InvalidOperationException("Cannot delete Root context");
            }

            if (composedOperationLogic.checkAndStartOperation(fl, new DeleteContext(fl.getContext(contextDBRepository, id)), user)) {
                boolean result = fl.delCtx(id, true);
                if(result) {
                    contextDBRepository.delete(id);
                    Thread.sleep(1000);
                    fl.restart();
                }
                return result;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PostMapping(path="/{id}/ruleDeveloper")
    public @ResponseBody
    Context addRuleDeveloper (@PathVariable(value="id") String id, @RequestBody User user) {
        try {
            ContextDB context = contextDBRepository.findOne(id);
            context.getRuleDevelopers().add(user);
            contextDBRepository.save(context);

            return getContextDetails(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @DeleteMapping(path="/{id}/ruleDeveloper/{userId}")
    public @ResponseBody
    Context deleteRuleDeveloper (@PathVariable(value="id") String id, @PathVariable(value="userId") Long userId) {
        try {
            ContextDB context = contextDBRepository.findOne(id);
            User user = userRepository.findOne(userId);
            context.getRuleDevelopers().remove(user);
            contextDBRepository.save(context);

            return getContextDetails(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PostMapping(path="/{id}/merge/{to}")
    public @ResponseBody
    boolean mergeContext (@PathVariable(value="id") String id, @PathVariable(value="to") String toId, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new MergeContext(fl.getContext(contextDBRepository, id), fl.getContext(contextDBRepository, toId)), user)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }

    @PostMapping(path="/{id}/split")
    public @ResponseBody
    boolean splitContext (@PathVariable(value="id") String id, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new SplitContext(fl.getContext(contextDBRepository, id)), user)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }

    }

    @PostMapping(path="/{id}/rule/{ruleId}/contextualize/{to}")
    public @ResponseBody
    boolean contextualizeRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId, @PathVariable(value="to") String toId, @RequestBody Rule rule, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new ContextualizeRule(rule, fl.getContext(contextDBRepository, id), fl.getContext(contextDBRepository, toId)), user)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }

    }

    @PostMapping(path="/{id}/rule/{ruleId}/decontextualize/{to}")
    public @ResponseBody
    boolean decontextualizeRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId, @PathVariable(value="to") String toId, @RequestBody Rule rule, @RequestHeader("User") Long user) {
        try (Flora2Repository fl = new Flora2Repository()) {
            if (composedOperationLogic.checkAndStartOperation(fl, new DecontextualizeRule(rule, fl.getContext(contextDBRepository, id), fl.getContext(contextDBRepository, toId)), user)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FatalFlora2Error(e);
        }
    }
}
