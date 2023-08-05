package org.shoopingKart.ShoopingApi.controller;

import java.util.List;

import org.shoopingKart.ShoopingApi.dto.AuthUser;
import org.shoopingKart.ShoopingApi.dto.ResponseStructure;
import org.shoopingKart.ShoopingApi.dto.User;
import org.shoopingKart.ShoopingApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	@Autowired
	 UserService service;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<User>> getUser(@PathVariable int id) {
		return service.getUser(id);

	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}

	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		return service.findAll();
	}
	
	@PostMapping("/user/verify-account")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestParam String email, @RequestParam String password){
		System.out.println("runing sucess");
			
		return service.validateUser(email, password);
		

	}

}
