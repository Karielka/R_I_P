package ru.daivinchik.feelings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.daivinchik.feelings.dto.UserProfileDTO;
import ru.daivinchik.feelings.entity.UserProfile; // Добавлено
import ru.daivinchik.feelings.repository.UserProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<UserProfileDTO> getAllUserProfiles() {
        return userProfileRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<UserProfileDTO> getUserProfileById(Long id) {
        return userProfileRepository.findById(id)
                .map(this::convertToDTO);
    }

    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = convertToEntity(userProfileDTO);
        userProfileRepository.save(userProfile);
        return convertToDTO(userProfile);
    }

    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Обновление полей профиля
        userProfile.setFirstName(userProfileDTO.getFirstName());
        userProfile.setLastName(userProfileDTO.getLastName());
        userProfile.setDateOfBirth(userProfileDTO.getDateOfBirth());
        userProfile.setGender(userProfileDTO.getGender());
        userProfile.setBio(userProfileDTO.getBio());
        userProfile.setLocation(userProfileDTO.getLocation());

        userProfileRepository.save(userProfile);
        return convertToDTO(userProfile);
    }

    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }

    // Преобразование DTO в Entity и наоборот
    private UserProfileDTO convertToDTO(UserProfile userProfile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(userProfile.getId());
        dto.setUserId(userProfile.getUserId());
        dto.setFirstName(userProfile.getFirstName());
        dto.setLastName(userProfile.getLastName());
        dto.setDateOfBirth(userProfile.getDateOfBirth());
        dto.setGender(userProfile.getGender());
        dto.setBio(userProfile.getBio());
        dto.setLocation(userProfile.getLocation());
        return dto;
    }

    private UserProfile convertToEntity(UserProfileDTO dto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(dto.getUserId());
        userProfile.setFirstName(dto.getFirstName());
        userProfile.setLastName(dto.getLastName());
        userProfile.setDateOfBirth(dto.getDateOfBirth());
        userProfile.setGender(dto.getGender());
        userProfile.setBio(dto.getBio());
        userProfile.setLocation(dto.getLocation());
        return userProfile;
    }
}
