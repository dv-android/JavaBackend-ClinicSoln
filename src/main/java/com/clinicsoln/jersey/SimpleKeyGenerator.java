package com.clinicsoln.jersey;
 

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class SimpleKeyGenerator implements KeyGenerator{
   
	@Override
	public Key generateKey() {
		System.out.println("generateKey called");
		String keyString = "simplekey";
		Key key  = new SecretKeySpec(keyString.getBytes(),0,keyString.getBytes().length,"DES");
		System.out.println("generateKey called");
	  
		return key;
		
	}
}
 