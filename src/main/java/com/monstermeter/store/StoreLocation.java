package com.monstermeter.store;

import java.time.LocalDateTime;

import com.monstermeter.user.User;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "store_locations")
@Data
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "added_by_user_id")
    private User addedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
