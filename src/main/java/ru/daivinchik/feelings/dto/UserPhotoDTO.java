package ru.daivinchik.feelings.dto;

import ru.daivinchik.feelings.entity.UserPhoto;

import java.time.LocalDateTime;

public class UserPhotoDTO {

    private Long id;
    private Long userProfileId;
    private boolean primary;
    private LocalDateTime uploadedAt;

    public UserPhotoDTO() {}

    public UserPhotoDTO(Long id, Long userProfileId, boolean primary, LocalDateTime uploadedAt) {
        this.id = id;
        this.userProfileId = userProfileId;
        this.primary = primary;
        this.uploadedAt = uploadedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }


    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public static UserPhotoDTO fromEntity(UserPhoto userPhoto) {
        return new UserPhotoDTO(
                userPhoto.getId(),
                userPhoto.getUserProfile().getId(),
                userPhoto.isPrimary(),
                userPhoto.getUploadedAt()
        );
    }

    public UserPhoto toEntity() {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(this.id);
        userPhoto.setPrimary(this.primary);
        userPhoto.setUploadedAt(this.uploadedAt);
        return userPhoto;
    }
}
