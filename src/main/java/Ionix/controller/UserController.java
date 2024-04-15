package Ionix.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ionix.entities.User;
import Ionix.service.UserService;
import Ionix.validation.Create;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<User> userOptional = userService.findById(id);
		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email){
		Optional<List<User>> userOptional = userService.findByEmail(email);
		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> created(@Validated(Create.class) @RequestBody User user, BindingResult result){
		
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		
		User userDB = userService.created(user);
		
		if(userDB.getId() == null)
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDB);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){
		
		if(result.hasFieldErrors()) {
			return validation(result);
		}
	
		Optional<User> userOptional = userService.update(user, id);
		
		if(userOptional.isPresent())
			return ResponseEntity.ok(userOptional.orElseThrow());
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<User> userOptional = userService.delete(id);
		
		if(userOptional.isPresent())
			return ResponseEntity.ok(userOptional);
		return ResponseEntity.notFound().build();
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
			
		Map<String, String> errors = new HashMap<>();
		
		result.getFieldErrors().forEach( err ->{
			errors.put(err.getField(), err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	
}
