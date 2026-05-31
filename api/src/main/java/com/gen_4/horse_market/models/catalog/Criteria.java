package com.gen_4.horse_market.models.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {

    private Float minWeight;
    private Float maxWeight;
    private Float minHeight;
    private Float maxHeight;
    private Double minPrice;
    private Double maxPrice;
    private String description;
    
}
