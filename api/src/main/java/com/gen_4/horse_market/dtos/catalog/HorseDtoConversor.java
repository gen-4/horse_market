package com.gen_4.horse_market.dtos.catalog;

import org.springframework.data.domain.Page;

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
            .isActive(horse.isActive())
            .build();
    }

    public Page<HorseDto> toHorsesDto(Page<Horse> horses) {
        return horses.map(horse -> toHorseDto(horse));
    }

    public Horse toHorse(HorseDto horseDto) {
        return Horse.builder()
            .name(horseDto.getName())
            .description(horseDto.getDescription())
            .height(horseDto.getHeight())
            .weight(horseDto.getWeight())
            .build();
    }

}
