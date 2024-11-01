package ru.daivinchik.feelings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.daivinchik.feelings.dto.UserProfileDTO;
import ru.daivinchik.feelings.service.UserProfileService;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/profiles")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public String getAllUserProfiles(Model model) {
        List<UserProfileDTO> userProfiles = userProfileService.getAllUserProfiles();
        model.addAttribute("userProfiles", userProfiles);
        return "profiles"; // Возвращаем имя шаблона
    }

    @PostMapping
    public String createUserProfile(@ModelAttribute UserProfileDTO userProfileDTO) {
        userProfileService.createUserProfile(userProfileDTO);
        return "redirect:/profiles"; // Перенаправление на список профилей
    }

    @GetMapping("/{id}")
    public String getUserProfileById(@PathVariable Long id, Model model) {
        UserProfileDTO userProfile = userProfileService.getUserProfileById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        model.addAttribute("userProfile", userProfile);
        return "editProfile"; // Шаблон для редактирования профиля
    }

    @PostMapping("/{id}")
    public String updateUserProfile(@PathVariable Long id, @ModelAttribute UserProfileDTO userProfileDTO) {
        userProfileService.updateUserProfile(id, userProfileDTO);
        return "redirect:/profiles"; // Перенаправление на список профилей
    }

    @PostMapping("/{id}/delete")
    public String deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return "redirect:/profiles"; // Перенаправление на список профилей
    }


// REST
//    @Autowired
//    private UserProfileService userProfileService;
//
//    @GetMapping
//    public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
//        List<UserProfileDTO> userProfiles = userProfileService.getAllUserProfiles();
//        return ResponseEntity.ok(userProfiles);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long id) {
//        return userProfileService.getUserProfileById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<UserProfileDTO> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
//        UserProfileDTO createdProfile = userProfileService.createUserProfile(userProfileDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfile);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO) {
//        UserProfileDTO updatedProfile = userProfileService.updateUserProfile(id, userProfileDTO);
//        return ResponseEntity.ok(updatedProfile);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
//        userProfileService.deleteUserProfile(id);
//        return ResponseEntity.noContent().build();
//    }
}
