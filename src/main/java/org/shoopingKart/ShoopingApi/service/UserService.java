package org.shoopingKart.ShoopingApi.service;

import java.util.List;
import java.util.Optional;

import org.shoopingKart.ShoopingApi.dao.UserDao;
import org.shoopingKart.ShoopingApi.dto.ResponseStructure;
import org.shoopingKart.ShoopingApi.dto.User;
import org.shoopingKart.ShoopingApi.exception.InvalidCredentialsException;
import org.shoopingKart.ShoopingApi.exception.idNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	public UserDao dao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user){
		user=dao.saveUser(user);
		ResponseStructure<User> reUser=new ResponseStructure<User>();
		reUser.setBody(user);
		reUser.setMessage("user save success");
		reUser.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(reUser,HttpStatus.ACCEPTED);
	}
   
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
 		ResponseStructure<User> s=new ResponseStructure<User>();
   	 s.setBody(dao.saveUser(user));
   	 s.setMessage("user updated succesfully");
   	 s.setCode(HttpStatus.ACCEPTED.value());
	return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.ACCEPTED);
 	}
 	
 	public ResponseEntity<ResponseStructure<User>> getUser(int id) {
 		ResponseStructure<User> s=new ResponseStructure<User>();
 		Optional<User>user=dao.getUser(id);
 		if(user!=null) {
 			 s.setBody(user.get());
 		   	 s.setMessage("user is Found");
 		   	 s.setCode(HttpStatus.FOUND.value());
 		   	 return new ResponseEntity<ResponseStructure<User>>(s,HttpStatus.FOUND);
 		}
 		else {
 			throw new idNotFoundException();
 		}
 		
 		
 	}
 	
 	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
 		ResponseStructure<String> s=new ResponseStructure<>();
 		Optional<User> user=dao.getUser(id);
 		if(user!=null) {
 			dao.deleteUser(id);
 			s.setBody("User is Found");
 			s.setMessage("user Delete Succesfully");
 			s.setCode(HttpStatus.FOUND.value());
 			return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FOUND);
 		}
 		else {
 			s.setBody("User is Not_Found");
 			s.setMessage("user Unable to Delete");
 			s.setCode(HttpStatus.NOT_FOUND.value());
 			return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
 		}
 		
 	}
 	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
 		ResponseStructure<List<User>> s=new  ResponseStructure<List<User>>();
 		s.setBody(dao.findAll());
 		s.setMessage("Found The All User");
 		s.setCode(HttpStatus.OK.value());
 		return new ResponseEntity<ResponseStructure<List<User>>>(s,HttpStatus.OK) ;
 	}
 	
 	public ResponseEntity<ResponseStructure<User>> validateUser(String email,String password){
 		Optional<User> user=dao.validateuser(email, password);
 		if(user != null) {
 			ResponseStructure<User> structure=new ResponseStructure<User>();
 			structure.setMessage("Login Sucess");
 			structure.setBody(user.get());
 			structure.setCode(HttpStatus.OK.value());
 			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
 		}
 		throw new InvalidCredentialsException();
 	}
 	
 	
}
