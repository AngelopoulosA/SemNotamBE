package app.Controller;

import app.Model.ContextDB;
import app.Model.Flora2.Rule;
import app.Model.User;
import app.Repository.ContextDBRepository;
import app.Repository.Flora2Repository;
import app.Repository.UserRepository;
import dke.pr.cli.CBRInterface;
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

    @Autowired
    public ContextController(ContextDBRepository contextDBRepository, UserRepository userRepository) {
        this.contextDBRepository = contextDBRepository;
        this.userRepository = userRepository;
    };

    @GetMapping(path="")
    public @ResponseBody
    List<String> getAllContextsFlat () {
        try (Flora2Repository fl = new Flora2Repository()) {
            return fl.getCtxs();
        } catch (IOException e) {
            return null;
        }
    }

    @GetMapping(path="/hierarchy")
    public @ResponseBody
    Context getContextHierarchy () {
        try (Flora2Repository fl = new Flora2Repository()) {

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
            context.setRules(getRules(fl, id));
            return context;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Rule> getRules(CBRInterface fl, String id) throws Exception {
        HashMap<String, String> rawRules = fl.getRules(id);
        List<Rule> rules = new LinkedList<>();
        for (Map.Entry<String, String> rule : rawRules.entrySet()) {
            rules.add(new Rule(rule.getKey(), rule.getValue().replace("@!{"+rule.getKey()+"}\r\n", "")));
        }
        return rules;
    }

    @PostMapping(path="/{id}/rule")
    public @ResponseBody
    Context addRule (@PathVariable(value="id") String id, @RequestBody Rule rule) {
        try (Flora2Repository fl = new Flora2Repository()) {

            boolean result = fl.addRule(id, "@!{"+rule.getId()+"}\r\n"+rule.getBody());
            if(result) {
                return getContextDetails(id);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(path="/{id}/rule/{ruleId}")
    public @ResponseBody
    Context deleteRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId) {
        try (Flora2Repository fl = new Flora2Repository()) {

            boolean result = fl.delRule(id, ruleId);
            return getContextDetails(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping(path="/{id}/rule/{ruleId}")
    public @ResponseBody
    Context addOrUpdateRule (@PathVariable(value="id") String id, @PathVariable(value="ruleId") String ruleId, @RequestBody Rule rule) {
        try (Flora2Repository fl = new Flora2Repository()) {

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
        try (Flora2Repository fl = new Flora2Repository()) {

            String ctxFile = fl.getCtxFileName(context.getName());
            String paramValues = context.getParameterValues().entrySet().stream().map(e -> e.getKey()+"->"+e.getValue()).collect(Collectors.joining(","));
            String ctxDefinition = context.getName()+":AIMCtx["+paramValues+",file->'"+ctxFile+"'].";
            boolean result = fl.addCtx(ctxDefinition, ctxFile);
            if(result) {
                Thread.sleep(1000);
                return getContextHierarchy();
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
        try (Flora2Repository fl = new Flora2Repository()) {

            boolean result = fl.delCtx(id, true);
            if(result) {
                Thread.sleep(1000);
                fl.restart();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
            return null;
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
            return null;
        }
    }
}
