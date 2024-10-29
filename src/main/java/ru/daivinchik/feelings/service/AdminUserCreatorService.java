package ru.daivinchik.feelings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.daivinchik.feelings.entity.User;
import ru.daivinchik.feelings.repository.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class AdminUserCreatorService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserCreatorService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createAdminUser() {
        // Проверяем, есть ли администратор в базе данных
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPasswordHash(passwordEncoder.encode("1234")); // Устанавливаем пароль
            admin.setEmail("karielka@yandex.ru");
            admin.setRole("ROLE_ADMIN"); // Устанавливаем роль администратора

            userRepository.save(admin);
        }
    }

    @PostConstruct
    public void createDefaultUsers() {
        if (userRepository.findByUsername("user1") == null) {
            User user1 = new User();
            user1.setUsername("user1");
            user1.setPasswordHash(passwordEncoder.encode("user123"));
            user1.setEmail("user1@example.com");
            user1.setRole("ROLE_USER");
            userRepository.save(user1);
        }

        if (userRepository.findByUsername("user2") == null) {
            User user2 = new User();
            user2.setUsername("user2");
            user2.setPasswordHash(passwordEncoder.encode("user123"));
            user2.setEmail("user2@example.com");
            user2.setRole("ROLE_USER");
            userRepository.save(user2);
        }
    }

}
