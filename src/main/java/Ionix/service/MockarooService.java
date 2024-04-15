package Ionix.service;

import org.springframework.http.ResponseEntity;

import Ionix.dto.Mackaroo;
import Ionix.dto.ObjectJson;

public interface MockarooService {

	ObjectJson objecJson();
	
	int countRegister(Mackaroo mackaroo);
	
	ObjectJson objectJson(Mackaroo mackaroo);
	
	ResponseEntity<Mackaroo> responseEntity();
	
	String cifraDES(String param, String key) throws Exception;
}
