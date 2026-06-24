package com.monstermeter.drink;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Drink getDrinkById(Long id) {
        return drinkRepository.findById(id).orElseThrow(() -> new RuntimeException("Drink not found by id: " + id));
    }

    public List<Drink> getDrinksByBrand(String brand) {
        return drinkRepository.findByBrand(brand);
    }

    public List<Drink> searchDrinks(String name) {
        return drinkRepository.findByNameContainingIgnoreCase(name);
    }

    public Drink createDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long id) {
        drinkRepository.deleteById(id);
    }
}
