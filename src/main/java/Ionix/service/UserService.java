package Ionix.service;

import java.util.List;
import java.util.Optional;


import Ionix.entities.User;

public interface UserService {

	User created(User user);
	
	Optional<User> update(User user, Long id);
	
	Optional<User> findById(Long id);
	
	List<User> findAll();
	
	Optional<User> delete(Long id);
	
	boolean existsByEmail(String email);
	
	Optional<List<User>> findByEmail(String email);
}
