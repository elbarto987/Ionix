package Ionix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import Ionix.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	boolean existsByEmail(String email);
	
	Optional<List<User>> findByEmail(String email);


}
