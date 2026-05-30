package com.gen_4.horse_market.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.RequiredArgsConstructor;

import com.gen_4.horse_market.models.catalog.Criteria;
import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.repositories.HorseRepository;
import com.gen_4.horse_market.services.CatalogServiceImpl;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public class HorseTests {

    @Autowired
    private CatalogServiceImpl catalogService;

    @Autowired
    private HorseRepository horseRepository;

    @BeforeAll
	public void beforeAll() {}

    @BeforeEach
    public void setUp() {
        Horse horse1 = Horse.builder()
            .name("Phoenix")
            .description("The most stubborn")
            .height(1.56f)
            .weight(430f)
            .build();
        
        horseRepository.saveAll(List.of(horse1));
    }

    @AfterEach
    public void tearDown() {
        horseRepository.deleteAll();
    }

    @Test
    public void getAllHorses() {
        Criteria criteria = Criteria.builder().build();

        List<Horse> horses = catalogService.getHorses(criteria);
        assertEquals(1, horses.size());
    }
  
}
