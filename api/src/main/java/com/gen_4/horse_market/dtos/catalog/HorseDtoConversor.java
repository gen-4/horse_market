package com.gen_4.horse_market.dtos.catalog;

import java.util.List;
import java.util.stream.Collectors;

import com.gen_4.horse_market.models.catalog.Horse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HorseDtoConversor {
    
    public HorseDto toHorseDto(Horse horse) {
        return HorseDto.builder()
            .id(horse.getId())
            .name(horse.getName())
            .description(horse.getDescription())
            .height(horse.getHeight())
            .weight(horse.getWeight())
            .build();
    }

    public List<HorseDto> toHorsesDto(List<Horse> horses) {
        return horses.stream().map(horse -> toHorseDto(horse)).collect(Collectors.toList());
    }

}
