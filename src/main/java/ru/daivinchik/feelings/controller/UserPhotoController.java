package ru.daivinchik.feelings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.daivinchik.feelings.service.UserPhotoService;

import java.io.IOException;

@Controller
@RequestMapping("/profiles/{userProfileId}/photos")
public class UserPhotoController {

    private final UserPhotoService userPhotoService;

    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }

    @GetMapping
    public String viewPhotos(@PathVariable Long userProfileId, Model model) {
        model.addAttribute("photos", userPhotoService.getUserPhotosByProfileId(userProfileId));
        model.addAttribute("userProfileId", userProfileId);
        return "photos/photoList";
    }

    @GetMapping("/add")
    public String showPhotoForm(@PathVariable Long userProfileId, Model model) {
        model.addAttribute("userProfileId", userProfileId);
        return "photos/photoForm";
    }

    @PostMapping("/save")
    public String savePhoto(
            @PathVariable Long userProfileId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("isPrimary") boolean isPrimary) {

        try {
            userPhotoService.saveUserPhoto(userProfileId, file, isPrimary);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/profiles/" + userProfileId + "/photos";
    }

    @GetMapping("/delete/{photoId}")
    public String deletePhoto(@PathVariable Long userProfileId, @PathVariable Long photoId) {
        userPhotoService.deletePhoto(photoId);
        return "redirect:/profiles/" + userProfileId + "/photos";
    }
}
