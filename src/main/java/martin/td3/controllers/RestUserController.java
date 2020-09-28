package martin.td3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import martin.td3.models.Organization;
import martin.td3.models.User;
import martin.td3.repositories.OrgaRepository;
import martin.td3.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class RestUserController extends RestMainController<User>{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	@GetMapping(value={"","/","index"})
	public @ResponseBody List<User> index() {
		return userRepo.findAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody String findUserById(@PathVariable int id) {
		Optional<User> opt = userRepo.findById(id);
		return opt.get().getFirstName();
		
	}
	
	@PostMapping(value={"","/","index"})
	public @ResponseBody User addUser(@RequestBody User user) {
		userRepo.saveAndFlush(user);
		return user;
	}
	
	@PatchMapping("{id}")
	public @ResponseBody User updateUser(@PathVariable int id,
			@Param("firstName") Optional<String> firstName,
			@Param("lastName") Optional<String> lastName,
			@Param("email") Optional<String> email,
			@Param("password") Optional<String> password) {
		Optional<User> opt = userRepo.findById(id);
		User toUpdateUser = null;
		if(opt.isPresent()) {
			toUpdateUser = opt.get();
			if (firstName.isPresent()) { toUpdateUser.setFirstName(firstName.get()); }
			if (lastName.isPresent()) { toUpdateUser.setLastName(lastName.get()); }
			if (email.isPresent()) { toUpdateUser.setEmail(email.get()); }
			userRepo.saveAndFlush(toUpdateUser);
		}
		return toUpdateUser;
	}
	
	@DeleteMapping("{id}")
	public @ResponseBody String deleteUser(@PathVariable int id) {
		Optional<User> opt = userRepo.findById(id);
		String userName = "User not find.";
		if (opt.isPresent()) {
			userName = opt.get().getFirstName() + " deleted !";
			userRepo.delete(opt.get());
		}
		return userName;
		
	}
}

