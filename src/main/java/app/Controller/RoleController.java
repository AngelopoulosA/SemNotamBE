package app.Controller;

import app.Model.Role;
import app.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@CrossOrigin
@RequestMapping(path="/roles")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @PostMapping(path="")
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

    @GetMapping(path = "")
    public @ResponseBody Iterable<Role> getAllRoles(){
        return roleRepository.findAll();
    }


    @GetMapping(path="/{id}")
    public @ResponseBody
    Role getParameterDetails (@PathVariable(value="id") long id) {
        return roleRepository.findOne(id);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody String deleteRoleById(@PathVariable(value="id") long id) {
        Role role = new Role();
        role = roleRepository.findOne(id);

        if(role != null){
            roleRepository.delete(role);
            return role.getName() + " is deleted";
        }

        return "Role cannot deleted!";
    }
}
