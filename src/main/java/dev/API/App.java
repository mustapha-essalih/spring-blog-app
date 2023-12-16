package dev.API;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.API.model.entity.Author;
import dev.API.model.entity.Roles;
import dev.API.model.entity.RootUser;
import dev.API.repository.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private UserRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
			// String passwordEncoded = passwordEncoder.encode("1234");
        
        	// List<Roles> authority = new ArrayList<>();

        	// authority.add(new Roles("ADMIN"));


			// RootUser rootUser = new RootUser("ADMIN" , passwordEncoded , authority);

			// repository.save(rootUser);
		
	}





}
