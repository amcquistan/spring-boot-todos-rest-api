package com.thecodinginterface.todobackend;

import com.thecodinginterface.todobackend.models.User;
import com.thecodinginterface.todobackend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.thecodinginterface.todobackend.models.UserAuthority.READ;
import static com.thecodinginterface.todobackend.models.UserAuthority.WRITE;

@SpringBootApplication
public class TodobackendApplication {

	@Bean
	CommandLineRunner demoData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			createSeedUser(userRepository, passwordEncoder, "adam", "adam", List.of(READ, WRITE));
			createSeedUser(userRepository, passwordEncoder, "marley", "marley", List.of(READ));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TodobackendApplication.class, args);
	}

	void createSeedUser(UserRepository userRepo, PasswordEncoder passwordEncoder, String username, String password, List<String> authorizations) {
		var existingUser = userRepo.findByUsername(username);
		if (existingUser == null) {
			var user = new User();
			user.setUsername(username);
			user.setPassword(passwordEncoder.encode(password));
			authorizations.forEach(a -> user.addAuthority(a));
			userRepo.save(user);
		}
	}
}
