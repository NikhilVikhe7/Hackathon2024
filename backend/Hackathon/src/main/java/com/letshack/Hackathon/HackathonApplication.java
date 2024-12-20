package com.letshack.Hackathon;

import com.letshack.Hackathon.model.User;
import com.letshack.Hackathon.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
			User user = new User();
			user.setName("John Doe");
			user.setEmail("john.doe@example.com");
			userRepository.save(user);
			System.out.println("User saved to the database!");
		};
	}

}
