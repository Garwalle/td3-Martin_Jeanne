package martin.td3.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import martin.td3.models.User;
import martin.td3.repositories.UserRepository;

@CrossOrigin("http://127.0.0.1:8080")
@RestController
@RequestMapping(value = { "/user", "/users" })
public class RestUserController extends RestMainController<User>{

	@Autowired
	public RestUserController(UserRepository userRepo) {
		super(userRepo);
	}

	@Override
	protected void updateObject(User toUpdateObject, HttpServletRequest request) {
		if (request.getParameter("firstName") != null) {
			toUpdateObject.setFirstName(request.getParameter("firstName"));
		}
		if (request.getParameter("lastName") != null) {
			toUpdateObject.setLastName(request.getParameter("lastName"));
		}
		if (request.getParameter("email") != null) {
			toUpdateObject.setEmail(request.getParameter("email"));
		}
		if (request.getParameter("password") != null) {
			toUpdateObject.setPassword(request.getParameter("password"));
		}
	}
}

