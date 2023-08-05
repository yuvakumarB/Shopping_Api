package org.shoopingKart.ShoopingApi.dao;

import java.util.List;
import java.util.Optional;

import org.shoopingKart.ShoopingApi.dto.User;
import org.shoopingKart.ShoopingApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	public UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	public User updateUser(User user) {
		return repository.save(user);
	}

	public Optional<User> getUser(int id) {
//		return repository.findById(id).get();
		return repository.findById(id);
		
//		Optional<User> recuser=repository.findById(id);
//		if(recuser.isEmpty()) {
//			return null;
//			
//		}
//		else
//			return recuser.get();
//
	}

	public void deleteUser(int id) {
		repository.deleteById(id);
	}
	public List<User> findAll(){
		return repository.findAll();
	}
    
	public Optional<User> validateuser(String email,String password) {
		return repository.validate(email, password);
	}

}
