package ru.daivinchik.feelings.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.daivinchik.feelings.dto.UserPhotoDTO;
import ru.daivinchik.feelings.entity.UserPhoto;
import ru.daivinchik.feelings.entity.UserProfile;
import ru.daivinchik.feelings.repository.UserPhotoRepository;
import ru.daivinchik.feelings.repository.UserProfileRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;
    private final UserProfileRepository userProfileRepository; // Добавить репозиторий для UserProfile

    public UserPhotoService(UserPhotoRepository userPhotoRepository, UserProfileRepository userProfileRepository) {
        this.userPhotoRepository = userPhotoRepository;
        this.userProfileRepository = userProfileRepository; // Инициализация
    }

    public void saveUserPhoto(Long userProfileId, MultipartFile file, boolean isPrimary) throws IOException {
        UserPhoto userPhoto = new UserPhoto();
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new IllegalArgumentException("UserProfile not found"));

        userPhoto.setUserProfile(userProfile); // Установить объект UserProfile
        userPhoto.setPhotoData(file.getBytes());
        userPhoto.setPrimary(isPrimary);
        userPhoto.setUploadedAt(LocalDateTime.now());

        userPhotoRepository.save(userPhoto);
    }

    public List<UserPhotoDTO> getUserPhotosByProfileId(Long userProfileId) {
        return userPhotoRepository.findByUserProfileId(userProfileId).stream()
                .map(UserPhotoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public void deletePhoto(Long photoId) {
        userPhotoRepository.deleteById(photoId);
    }
}
