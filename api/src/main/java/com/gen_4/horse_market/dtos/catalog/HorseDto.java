package com.gen_4.horse_market.dtos.catalog;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HorseDto {
    
    private long id;
    private String name;
    private String description;
    private float height;
    private float weight;
    private boolean isActive;

}
