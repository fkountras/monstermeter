package com.monstermeter.friendship;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByRequesterId(Long requesterId);

    List<Friendship> findByReceiverId(Long receiverId);

    List<Friendship> findByReceiverIdAndStatus(Long receiverId, FriendshipStatus status);

    List<Friendship> findByRequesterIdAndStatus(Long requesterId, FriendshipStatus status);

    Optional<Friendship> findByRequesterIdAndReceiverId(Long requesterId, Long receiver);
}
