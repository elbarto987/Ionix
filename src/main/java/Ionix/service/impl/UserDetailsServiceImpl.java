package Ionix.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Value("${app.username}")
	private String username;

	@Value("${app.password}")
	private String password;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username == null || password == null) {
			throw new UsernameNotFoundException("El usuario o la contraseña no se encuentran, contacte a servicio tecnico");
		}
				
		String passwordTemp = passwordEncoder.encode(password); 
       
		return User.withUsername(username)
				.password(passwordTemp)
				.roles("ADMIN")
				.build();
	}
}
