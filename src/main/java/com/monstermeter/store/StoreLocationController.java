package com.monstermeter.store;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreLocationController {
    private final StoreLocationService storeLocationService;

    @GetMapping
    public List<StoreLocation> getAllStores() {
        return storeLocationService.getAllStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreLocation> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeLocationService.getStoreById(id));
    }

    @GetMapping("/user/{userId}")
    public List<StoreLocation> getStoresByUser(@PathVariable Long userId) {
        return storeLocationService.getStoresByUser(userId);
    }

    @PostMapping
    public ResponseEntity<StoreLocation> createStore(@RequestBody StoreLocationRequest request) {
        return ResponseEntity.ok(storeLocationService.createStore(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeLocationService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
