package app.Controller;

import app.Model.Role;
import app.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(path="/role")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewRole (@RequestBody Role role) {

        if (role.getName().isEmpty()|| role.getDescription().isEmpty()){
            return "missing parameter";
        }

        Role newRole = new Role();

        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());

        roleRepository.save(newRole);
        return "Saved" + newRole.getName() + " " + newRole.getDescription();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @RequestMapping(value="/role", params="id", method = GET)
    public @ResponseBody Role getRoleById(@RequestParam("id") long id) {
        return roleRepository.findOne(id);
    }
}
