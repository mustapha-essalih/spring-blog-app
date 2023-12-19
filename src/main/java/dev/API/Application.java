package dev.API;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.API.entity.Roles;
import dev.API.entity.Tags;
import dev.API.entity.User;
import dev.API.repository.TagsRepository;
import dev.API.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private TagsRepository tagsRepository;

	@Override
	public void run(String... args) throws Exception {
		
		if (!userRepository.findByUsername("admin").isPresent()) {

			String[] arrayOfTags = {
				"Android" ,"Anime" ,"Art" ,"Beauty" ,"Blogging" ,"Books" ,"Business" ,"Cars" 
				,"Celebrities" ,"Comedy" ,"Design" ,"Events" ,"Exercise" ,"Fashion" ,"Film" 
				,"Fitness" ,"Gaming" ,"Health" ,"Hip Hop" ,"History" ,"Lifestyle"
			};
			
			List<Tags> tags = new ArrayList<>();

			for (String str : arrayOfTags) {
				tags.add(new Tags(str));
			}

			String passwordEncoded = encoder.encode("1234");

			User user = new User("admin" , "admin@admin.com" , passwordEncoded , Roles.ROLE_ADMIN);

			tagsRepository.saveAll(tags);
			userRepository.save(user);
		}
		
	
	}

}
