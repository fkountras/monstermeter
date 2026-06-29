package com.monstermeter.drink;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drinks")
@Data
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String brand;

    @Column
    private Integer calories;

    @Column
    private Integer caffeine;

    @Column(name = "volume_ml")
    private Integer volumeMl;

    @Column
    private String flavour;

    @Column(name = "image_url")
    private String imageUrl;
}