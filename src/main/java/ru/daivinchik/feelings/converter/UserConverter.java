package ru.daivinchik.feelings.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.daivinchik.feelings.dto.UserDto;
import ru.daivinchik.feelings.entity.User;

@Component
public class UserConverter {

    private final BCryptPasswordEncoder passwordEncoder;

    public UserConverter(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    public UserDto convertToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        // Пароль не передаем для безопасности
        return dto;
    }
}
