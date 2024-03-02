package com.example.auction.repository;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.SellerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
// this is not very valid test it skips the Controller/Service layer where soem logic/validation is performed
// this is useful only for checking the DB layer
@SpringBootTest
class SellerRepositoryTest {

    @Autowired
    private SellerRepository repository;

    @Test
    void shouldSaveBook(){
        //given
        AuctionModel auction1 = new AuctionModel("test auction1",10.0,25.0,"book1", LocalDateTime.now());
        AuctionModel auction2 = new AuctionModel("test auction2",5.0,50.0,"book2", LocalDateTime.now());
        Set<AuctionModel> auction = Set.of(auction1);
        SellerModel bob = new SellerModel("Bob", auction);
        //when
        SellerModel saved = repository.save(bob);
        // then
        assertEquals("bob",saved.getName());
    }
}