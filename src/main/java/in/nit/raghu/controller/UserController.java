package in.nit.raghu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nit.raghu.entity.User;
import in.nit.raghu.entity.UserRequest;
import in.nit.raghu.entity.UserResponse;
import in.nit.raghu.service.IUserService;
import in.nit.raghu.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService service;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id = service.saveUser(user);
		return ResponseEntity.ok("User saved with id" + id);
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));

		String token = jwtUtil.generateToken(userRequest.getUsername());

		return ResponseEntity.ok(new UserResponse(token, "GENERATED BY MR.ANSH -NIT"));
	}

	@PostMapping("/welcome")
	public ResponseEntity<String> accessUserData(Principal p) {
		return ResponseEntity.ok("Hello user:" + p.getName());
	}

}
