package app.Controller;

import app.Model.Role;
import app.Model.User;
import app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller    // This means that this class is a Controller
@RequestMapping(path="/users") // This means URL's start with /demo (after Application path)
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping(path="") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestBody User user) {

		if(user.getName().isEmpty()|| user.getPassword().isEmpty()||user.getUsername().isEmpty()){
			return "User Parameter is missing";
		}

		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setRole(user.getRole());
		userRepository.save(newUser);
		return "Saved";
	}
	
	@GetMapping(path="")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path="/ruleDevelopers")
	public @ResponseBody Iterable<User> getRuleDevelopers() {
		return userRepository.findAllByRole(Role.RuleDeveloper);
	}

	@DeleteMapping(path="/{id}")
	public @ResponseBody String deleteUserById(@PathVariable(value="id") long id) {
		User user = new User();
		user = userRepository.findOne(id);

		if(user != null){
			userRepository.delete(user);
			return user.getUsername() + " is deleted";
		}

		return "User cannot deleted!";
	}

    @PostMapping(path="/login")
    public @ResponseBody User login(@RequestBody User loginRequest) {
        User user = userRepository.findFirstByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        return user;
    }

	@GetMapping(path="/{id}")
	public @ResponseBody
	User getParameterDetails (@PathVariable(value="id") long id) {
		return userRepository.findOne(id);
	}
}
