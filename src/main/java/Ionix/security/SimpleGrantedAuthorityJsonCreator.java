package Ionix.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

abstract public class SimpleGrantedAuthorityJsonCreator {

	@JsonCreator
	public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {
		
	}
}
