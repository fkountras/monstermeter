package com.monstermeter.drink;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByBrand(String brand);

    List<Drink> findByNameContainingIgnoreCase(String name);
}
