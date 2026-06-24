package com.monstermeter.consumption;

import java.time.LocalDateTime;
import com.monstermeter.drink.Drink;
import com.monstermeter.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "consumption_logs")
@Data
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(name = "logged_at")
    private LocalDateTime loggedAt = LocalDateTime.now();

    @Column
    private String notes;
}
