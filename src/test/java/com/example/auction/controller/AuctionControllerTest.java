package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuctionControllerTest {
    @Test
    void shouldAddAuction(@Autowired WebTestClient testClient) {
        testClient
                .post()
                .uri("/auction")
                .bodyValue(new AuctionModel("test auction", 1.0, 1.0, "test description", LocalDateTime.now()))
                .exchange()
                .expectStatus().isCreated();
    }
}