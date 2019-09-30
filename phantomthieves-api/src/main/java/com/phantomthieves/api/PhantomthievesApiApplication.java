package com.phantomthieves.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PhantomthievesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhantomthievesApiApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}
