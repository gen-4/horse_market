package com.gen_4.horse_market.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import com.gen_4.horse_market.models.user.Role;
import com.gen_4.horse_market.models.user.RoleOptions;
import com.gen_4.horse_market.models.user.User;
import com.gen_4.horse_market.repositories.HorseRepository;
import com.gen_4.horse_market.repositories.RoleRepository;
import com.gen_4.horse_market.repositories.UserRepository;
import com.gen_4.horse_market.services.CatalogServiceImpl;


@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public class HorseTests {

    @Autowired
    private CatalogServiceImpl catalogService;

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeAll
	public void beforeAll() {}

    @BeforeEach
    public void setUp() {
        Role userRole = Role.builder()
            .role(RoleOptions.USER)
            .build();
        
        userRole = roleRepository.save(userRole);
            
        User userUser = User.builder()
            .username("basicUser")
            .password("1234")
            .registerDate(Timestamp.valueOf(LocalDateTime.now()))
            .roles(List.of(userRole))
            .build();

        userUser = userRepository.save(userUser);

        Horse horse1 = Horse.builder()
            .name("Phoenix")
            .description("The most stubborn")
            .height(1.56f)
            .weight(430f)
            .owner(userUser)
            .build();
        
        horseRepository.saveAll(List.of(horse1));
    }

    @AfterEach
    public void tearDown() {
        horseRepository.deleteAll();
    }

    /* @Test
    public void getAllHorses() { // TODO: This test is just an example. Remove it when any other is created
        Criteria criteria = Criteria.builder().build();

        Page<Horse> horses = catalogService.getHorses(criteria, PageRequest.of(0, 10));
        assertEquals(1, horses.getNumber());
    } */
  
}
