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

    public Drink updateDrink(Long id, Drink updated) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drink not found by id: " + id));
        drink.setName(updated.getName());
        drink.setBrand(updated.getBrand());
        drink.setCaffeine(updated.getCaffeine());
        drink.setCalories(updated.getCalories());
        drink.setFlavour(updated.getFlavour());
        drink.setVolumeMl(updated.getVolumeMl());
        return drinkRepository.save(drink);
    }

    public Drink patchDrink(Long id, Drink updated) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drink not found by id: " + id));
        if (updated.getName() != null)
            drink.setName(updated.getName());
        if (updated.getBrand() != null)
            drink.setBrand(updated.getBrand());
        if (updated.getCaffeine() != null)
            drink.setCaffeine(updated.getCaffeine());
        if (updated.getCalories() != null)
            drink.setCalories(updated.getCalories());
        if (updated.getFlavour() != null)
            drink.setFlavour(updated.getFlavour());
        if (updated.getVolumeMl() != null)
            drink.setVolumeMl(updated.getVolumeMl());
        return drinkRepository.save(drink);
    }
}
