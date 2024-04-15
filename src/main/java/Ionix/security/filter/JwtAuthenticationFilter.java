package Ionix.security.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Ionix.dto.UsuarioDto;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static Ionix.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	
	@Value("${app.username}")
	private String username;

	@Value("${app.password}")
	private String password;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		 UsuarioDto usuario = null;
		 String username = null;
	     String password = null;

	        try {
	        	usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioDto.class);
	            username = usuario.getUsername();
	            password = usuario.getPassword();
	        } catch (StreamReadException e) {
	            e.printStackTrace();
	        } catch (DatabindException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
;
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		User user = (User) authResult.getPrincipal();
		String username = user.getUsername();
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		
		String token = Jwts.builder()
						.subject(username)
						.claims(Jwts.claims().add("authorities", new ObjectMapper().writeValueAsString(roles)).add("username", username).build())
						.expiration(new Date(System.currentTimeMillis() + 600000))
						.issuedAt(new Date())
						.signWith(SECRET_KEY)
						.compact();
		
		response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
		
		Map<String, String> body = new HashMap<>();
		
		body.put("token", token);
		body.put("message", String.format("Hola %s has iniciado sesion con exito!", username));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType(CONTENT_TYPE);
		response.setStatus(200);

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		
		Map<String, String> body = new HashMap<>();
		
		body.put("message", "Error en la autenticacion, el usuario o la contraseña son incorrectos!");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType(CONTENT_TYPE);
	}
	
	/*public static class Usuario{
		
		private String username;
		
		private String password;

		public Usuario() {

		}

		public Usuario(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		
	}*/

}
