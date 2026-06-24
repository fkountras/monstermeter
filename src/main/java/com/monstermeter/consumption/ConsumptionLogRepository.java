package com.monstermeter.consumption;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumptionLogRepository extends JpaRepository<ConsumptionLog, Long> {
    List<ConsumptionLog> findByUserId(Long userId);

    List<ConsumptionLog> findByDrinkId(Long drinkId);
}
