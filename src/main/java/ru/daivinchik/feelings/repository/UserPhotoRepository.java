package ru.daivinchik.feelings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daivinchik.feelings.entity.UserPhoto;

import java.util.List;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {

    // Метод для получения всех фотографий конкретного профиля пользователя
    List<UserPhoto> findByUserProfileId(Long userProfileId);

    // Метод для получения основной фотографии конкретного профиля пользователя
    UserPhoto findByUserProfileIdAndPrimaryTrue(Long userProfileId);
}
