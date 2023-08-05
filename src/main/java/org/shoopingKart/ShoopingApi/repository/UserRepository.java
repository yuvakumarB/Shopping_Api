package org.shoopingKart.ShoopingApi.repository;

import java.util.Optional;

import org.shoopingKart.ShoopingApi.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.email=?1 and u.password=?2")
	public Optional<User> validate(String email,String password);

}
