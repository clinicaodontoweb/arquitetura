package com.odontoweb.arquitetura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.odontoweb.arquitetura.security.JWTAuthorizationUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {

	@Bean
	public JWTAuthorizationUtil authorizationUtils() {
		return new JWTAuthorizationUtil();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return getMd5(charSequence.toString());
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return getMd5(charSequence.toString()).equals(s);
			}
		};
	}

	public static String getMd5(String input) {
		try {
			// Static getInstance method is called with hashing SHA
			MessageDigest md = MessageDigest.getInstance("MD5");

			// digest() method called
			// to calculate message digest of an input
			// and return array of byte
			byte[] messageDigest = md.digest(input.getBytes());

			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);

			// Convert message digest into hex value
			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown"
					+ " for incorrect algorithm: " + e);
			return null;
		}


	}
}
