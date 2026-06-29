package com.monstermeter.consumption;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ConsumptionLogResponseDTO {
    private Long id;
    private Long userId;
    private String username;
    private Long drinkId;
    private String drinkName;
    private String drinkImageUrl;
    private LocalDateTime loggedAt;
    private String notes;

    public static ConsumptionLogResponseDTO from(ConsumptionLog log) {
        ConsumptionLogResponseDTO dto = new ConsumptionLogResponseDTO();
        dto.setId(log.getId());
        dto.setUserId(log.getUser().getId());
        dto.setUsername(log.getUser().getUsername());
        dto.setDrinkId(log.getDrink().getId());
        dto.setDrinkName(log.getDrink().getName());
        dto.setDrinkImageUrl(log.getDrink().getImageUrl());
        dto.setLoggedAt(log.getLoggedAt());
        dto.setNotes(log.getNotes());
        return dto;
    }
}
