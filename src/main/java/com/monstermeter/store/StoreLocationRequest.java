package com.monstermeter.store;

import lombok.Data;

@Data
public class StoreLocationRequest {
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private Long addedByUserId;
}
