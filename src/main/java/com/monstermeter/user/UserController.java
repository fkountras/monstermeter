package com.monstermeter.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResponseDTO::from).toList();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(UserResponseDTO.from(userService.registerUser(userDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO dto) {
        try {
            UserResponseDTO response = userService.login(dto.getUsername(), dto.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}