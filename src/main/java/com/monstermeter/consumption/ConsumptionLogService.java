package com.monstermeter.consumption;

import java.util.List;
import org.springframework.stereotype.Service;

import com.monstermeter.drink.Drink;
import com.monstermeter.drink.DrinkRepository;
import com.monstermeter.user.User;
import com.monstermeter.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsumptionLogService {

    private final ConsumptionLogRepository consumptionLogRepository;
    private final UserRepository userRepository;
    private final DrinkRepository drinkRepository;

    public List<ConsumptionLog> getLogsByUser(Long userId) {
        return consumptionLogRepository.findByUserId(userId);
    }

    public List<ConsumptionLog> getLogsByDrink(Long drinkId) {
        return consumptionLogRepository.findByDrinkId(drinkId);
    }

    public ConsumptionLog createLog(Long userId, Long drinkId, String notes) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Drink drink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new RuntimeException("Drink not found with id: " + drinkId));

        ConsumptionLog log = new ConsumptionLog();
        log.setUser(user);
        log.setDrink(drink);
        log.setNotes(notes);

        return consumptionLogRepository.save(log);
    }

    public void deleteLog(Long id) {
        consumptionLogRepository.deleteById(id);
    }

    public ConsumptionLog updateNotes(Long id, String notes) {
        ConsumptionLog log = consumptionLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found with id: " + id));
        log.setNotes(notes);
        return consumptionLogRepository.save(log);
    }
}
