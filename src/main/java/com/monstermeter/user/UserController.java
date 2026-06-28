package com.monstermeter.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

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

    @GetMapping("/search")
    public List<UserResponseDTO> searchUsers(@RequestParam String username) {
        return userService.searchByUsername(username).stream()
                .map(UserResponseDTO::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserResponseDTO.from(user));
    }

    @PatchMapping("/{id}/profile-picture")
    public ResponseEntity<UserResponseDTO> updateProfilePicture(@PathVariable Long id,
            @RequestBody Map<String, String> body) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setProfilePicture(body.get("profilePicture"));
        userRepository.save(user);
        return ResponseEntity.ok(UserResponseDTO.from(user));
    }
}