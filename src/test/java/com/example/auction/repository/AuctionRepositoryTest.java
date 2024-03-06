package com.example.auction.repository;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.CategoryModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuctionRepositoryTest {
    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldGetAuctionsByCategory() {

        Optional<CategoryModel> motoCategory = categoryRepository.findByName("Moto");

        List<AuctionModel> res = auctionRepository.findByCategory(motoCategory.get());

        assertEquals(2, res.size());
    }
}
//    @Test
//    void shouldFindAuctionByQueryString() {
//
//        List<AuctionModel> auctions = auctionRepository.findAuctionBy("toyota");
//
//        assertEquals(1, auctions.size());
//    }
//}