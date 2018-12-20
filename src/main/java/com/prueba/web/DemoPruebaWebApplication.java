package com.prueba.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
import com.prueba.web.models.Role;
import com.prueba.web.repositories.RoleRepository;
*/
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoPruebaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoPruebaWebApplication.class, args);
	}
/*	
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

	    return args -> {

	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }

	        Role userRole = roleRepository.findByRole("USER");
	        if (userRole == null) {
	            Role newUserRole = new Role();
	            newUserRole.setRole("USER");
	            roleRepository.save(newUserRole);
	        }
	    };

	}
*/
}
