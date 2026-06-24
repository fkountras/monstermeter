package com.monstermeter.friendship;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/friendships")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/request")
    public ResponseEntity<Friendship> sendRequest(@RequestParam Long requesterId, @RequestParam Long receiverId) {
        return ResponseEntity.ok(friendshipService.sendRequest(requesterId, receiverId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Friendship> updateStatus(@PathVariable Long id, @RequestParam FriendshipStatus status) {
        return ResponseEntity.ok(friendshipService.updateStatus(id, status));
    }

    @GetMapping("/sent/{userId}")
    public List<Friendship> getSentRequests(@PathVariable Long userId) {
        return friendshipService.getSentRequests(userId);
    }

    @GetMapping("/pending/{userId}")
    public List<Friendship> getPendingRequests(@PathVariable Long userId) {
        return friendshipService.getPendingRequests(userId);
    }

    @GetMapping("/friends/{userId}")
    public List<Friendship> getFriends(@PathVariable Long userId) {
        return friendshipService.getFriends(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteFriendship(@PathVariable Long id) {
        friendshipService.deleteFriendship(id);
        return ResponseEntity.noContent().build();
    }
}
