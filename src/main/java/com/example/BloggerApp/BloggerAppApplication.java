package com.example.BloggerApp;

import com.example.BloggerApp.configurations.AppConstants;
import com.example.BloggerApp.models.Roles;
import com.example.BloggerApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BloggerAppApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BloggerAppApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("test"));

		Roles role1 = new Roles(AppConstants.ADMIN_USER,"ROLE_ADMIN");
		Roles role2 = new Roles(AppConstants.NORMAL_USER,"ROLE_NORMAL");

		List<Roles> rolesList = List.of(role1,role2);

		roleRepository.saveAll(rolesList);
	}
}
