package Ionix.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Ionix.dto.Mackaroo;
import Ionix.dto.ObjectJson;
import Ionix.service.MockarooService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class MockarooServiceImpl implements MockarooService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.url.mackaroo}")
	private String url;
	
	@Value("${api.param.mackaroo}")
	private String paramUrl;
	
	@Value("${api.key.mackaroo}")
	private String keyEncry;
	
	@Override
	public ObjectJson objecJson() {
		
		Long starTime = System.currentTimeMillis();
		
		ResponseEntity<Mackaroo> responseEntity = responseEntity();
		
		Long endTime = System.currentTimeMillis();
		
		Long time = endTime - starTime;
		
		ObjectJson json = objectJson(responseEntity.getBody());
		
		json.setElapsedTime(time);
	
		return json;
	}

	@Override
	public int countRegister(Mackaroo mackaroo) {
		return mackaroo.getResult().getItems().size();
	}

	@Override
	public ObjectJson objectJson(Mackaroo mackaroo) {
		
		ObjectJson json = new ObjectJson(ObjectJson.newResult(countRegister(mackaroo)));
		json.setResponseCode(mackaroo.getResponseCode());
		json.setDescription(mackaroo.getDescription());
		json.setElapsedTime(null);
		
		return json;
	}

	@Override
	public ResponseEntity<Mackaroo> responseEntity() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-API-Key", "f2f719e0");
		
		try {
			url += cifraDES(paramUrl, keyEncry);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResponseEntity<Mackaroo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Mackaroo.class); 
		
		return responseEntity;
	}


	@Override
	public String cifraDES(String param, String key) throws Exception {

		byte[] bytesTexto = param.getBytes(StandardCharsets.UTF_8);
        KeySpec keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretkey = keyFactory.generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);

        byte[] bytesCifrados = cipher.doFinal(bytesTexto);

        return Base64.getEncoder().encodeToString(bytesCifrados);
		
		
	}

	

}
