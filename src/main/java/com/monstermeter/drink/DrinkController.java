package com.monstermeter.drink;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drinks")
@RequiredArgsConstructor
public class DrinkController {
    private final DrinkService drinkService;

    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable Long id) {
        return ResponseEntity.ok(drinkService.getDrinkById(id));
    }

    @GetMapping("/search")
    public List<Drink> searchDrinks(@RequestParam String name) {
        return drinkService.searchDrinks(name);
    }

    @GetMapping("/brand/{brand}")
    public List<Drink> getDrinksByBrand(@PathVariable String brand) {
        return drinkService.getDrinksByBrand(brand);
    }

    @PostMapping
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
        return ResponseEntity.ok(drinkService.createDrink(drink));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);
        return ResponseEntity.noContent().build();
    }

}
