package tech.vaibhavlachhwani.fiscusbackend.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tech.vaibhavlachhwani.fiscusbackend.entity.User;
import tech.vaibhavlachhwani.fiscusbackend.enums.Role;
import tech.vaibhavlachhwani.fiscusbackend.repository.UserRepository;

@Component
@Order(1)
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByRole(Role.ADMIN)) {
            User genesisAdmin = User.builder()
                    .email("admin@fiscus.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .isActive(true)
                    .build();

            userRepository.save(genesisAdmin);

            System.out.println("=========================================================");
            System.out.println("DATABASE SEEDER: Master Admin created successfully.");
            System.out.println("Email: admin@fiscus.com");
            System.out.println("Password: admin123");
            System.out.println("=========================================================");
        } else {
            System.out.println("DATABASE SEEDER: Admin account already exists. Skipping genesis creation.");
        }
    }
}
