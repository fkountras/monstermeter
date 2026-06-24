package com.monstermeter.consumption;

import lombok.Data;

@Data
public class ConsumptionLogRequest {
    private Long userId;
    private Long drinkId;
    private String notes;
}
