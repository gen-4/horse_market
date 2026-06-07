package com.gen_4.horse_market.dtos.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorseDto {
    
    private Long id;
    private String name;
    private String description;
    private Float height;
    private Float weight;
    private Boolean isActive;

}
