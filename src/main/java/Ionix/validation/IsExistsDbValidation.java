package Ionix.validation;

import org.springframework.beans.factory.annotation.Autowired;

import Ionix.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String>{
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(userService == null) {
			return true;
		}
		
		return !userService.existsByEmail(value);
	}

}
