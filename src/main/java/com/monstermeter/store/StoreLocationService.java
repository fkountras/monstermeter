package com.monstermeter.store;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monstermeter.user.User;
import com.monstermeter.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreLocationService {

    private final StoreLocationRepository storeLocationRepository;
    private final UserRepository userRepository;

    public List<StoreLocation> getAllStores() {
        return storeLocationRepository.findAll();
    }

    public StoreLocation getStoreById(Long id) {
        return storeLocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    public List<StoreLocation> getStoresByUser(Long userId) {
        return storeLocationRepository.findByAddedById(userId);
    }

    public StoreLocation createStore(StoreLocationRequest request) {
        User user = userRepository.findById(request.getAddedByUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getAddedByUserId()));

        StoreLocation store = new StoreLocation();
        store.setName(request.getName());
        store.setLatitude(request.getLatitude());
        store.setLongitude(request.getLongitude());
        store.setAddress(request.getAddress());
        store.setAddedBy(user);

        return storeLocationRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeLocationRepository.deleteById(id);
    }
}
