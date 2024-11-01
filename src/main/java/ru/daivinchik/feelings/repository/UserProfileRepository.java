package ru.daivinchik.feelings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daivinchik.feelings.entity.UserProfile;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // Метод для поиска профиля по userId
    Optional<UserProfile> findByUserId(Long userId);
}
