package Ionix.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Ionix.entities.User;
import Ionix.repository.UserRepository;
import Ionix.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User created(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public Optional<User> update(User user, Long id) {
		
		Optional<User> userOptional = this.findById(id);
		
		if(userOptional.isPresent()) {
			
			User userDB = userOptional.orElseThrow();
			
			userDB.setName(user.getName());
			userDB.setUsername(user.getUsername());
			userDB.setEmail(userDB.getEmail());
			userDB.setPhone(user.getPhone());
			
			return Optional.of(userRepository.save(userDB));
		}
		
		return userOptional;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<User> delete(Long id) {
		
		Optional<User> userOptional = this.findById(id);
		
		userOptional.ifPresent(user -> userRepository.delete(user));
		return userOptional;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<User>> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
