package com.monstermeter.friendship;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monstermeter.user.User;
import com.monstermeter.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public Friendship sendRequest(Long requesterId, Long receiverId) {
        if (requesterId.equals(receiverId)) {
            throw new RuntimeException("You cannot send a friend request to yourself");
        }

        friendshipRepository.findByRequesterIdAndReceiverId(requesterId, receiverId).ifPresent(f -> {
            throw new RuntimeException("Friend request already exists");
        });

        User requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("User not found: " + requesterId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("User not found: " + receiverId));

        Friendship friendship = new Friendship();
        friendship.setRequester(requester);
        friendship.setReceiver(receiver);

        return friendshipRepository.save(friendship);
    }

    public Friendship updateStatus(Long friendshipId, FriendshipStatus status) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Friendship not found: " + friendshipId));
        friendship.setStatus(status);
        return friendshipRepository.save(friendship);
    }

    public List<Friendship> getSentRequests(Long userId) {
        return friendshipRepository.findByRequesterId(userId);
    }

    public List<Friendship> getPendingRequests(Long userId) {
        return friendshipRepository.findByReceiverIdAndStatus(userId, FriendshipStatus.PENDING);
    }

    public List<Friendship> getFriends(Long userId) {
        List<Friendship> asReceiver = friendshipRepository.findByReceiverIdAndStatus(userId, FriendshipStatus.ACCEPTED);
        List<Friendship> asRequester = friendshipRepository.findByRequesterIdAndStatus(userId,
                FriendshipStatus.ACCEPTED);
        List<Friendship> all = new java.util.ArrayList<>(asReceiver);
        all.addAll(asRequester);
        return all;
    }

    public void deleteFriendship(Long id) {
        friendshipRepository.deleteById(id);
    }
}
