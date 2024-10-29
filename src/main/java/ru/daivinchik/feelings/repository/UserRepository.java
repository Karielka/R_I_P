package ru.daivinchik.feelings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.daivinchik.feelings.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Добавляем метод
}
