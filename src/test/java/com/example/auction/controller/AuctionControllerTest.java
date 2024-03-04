package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.CategoryModel;
import com.example.auction.model.SellerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuctionControllerTest {
    @Test
    void shouldFindAuctionByQuery(@Autowired WebTestClient testClient) {
        testClient
                .get()
                .uri("/auction/search?query=opla%20sprzedam")
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void shouldAddAuctionToDb(@Autowired WebTestClient testClient) {
        testClient
                .post()
                .uri("/auction/add")
                .bodyValue(new AuctionModel("test auction", 1.0, 10.0, "test description", LocalDateTime.now(), new SellerModel(),new CategoryModel("Moto")))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void shouldReturn400ForNotExistingCategory(@Autowired WebTestClient testClient) {
        testClient
                .post()
                .uri("/auction")
                .bodyValue(new AuctionModel("test auction", 1.0, 15.0, "test description", LocalDateTime.now(),new SellerModel(), new CategoryModel("Non Existing Category")))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().json("""
                        {"type":"about:blank","title":"Bad Request","status":400,"detail":"Category Non Existing Category not exist","instance":"/auctions"}""");
    }

    @Test
    void shouldDeleteAuction(@Autowired WebTestClient testClient) {
        testClient
                .delete()
                .uri("/auction/1")
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isNoContent();
    }
    @Test
    void shouldUpdateAuction(@Autowired WebTestClient testClient) {
        AuctionModel updatedAuction = testClient
                .put()
                .uri("/auction/1")
                .bodyValue(new AuctionModel("Updated", 1.0, 15.0, "test description", LocalDateTime.now(),new SellerModel(),new CategoryModel()))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuctionModel.class).returnResult().getResponseBody();

        assertEquals("Updated", updatedAuction.getName());
    }
    @Test
    void shouldCheckIfValidationIsWorking(@Autowired WebTestClient testClient) {
        testClient
                .post()
                .uri("/auction")
                .bodyValue(new AuctionModel("test auction", -1.0, 15.0, "test description", LocalDateTime.now()))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().json("""
                        {"type": "about:blanc"}""");
    }
    @Test
    void shouldReturn400whenUpdateAuctionWithWrongId(@Autowired WebTestClient testClient) {
        testClient
                .put()
                .uri("/auction/999")
                .bodyValue(new AuctionModel("Updated", 1.0, 15.0, "test description", LocalDateTime.now()))
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isNotFound();
    }
    @Test
    void shouldSaveAuction() {
        AuctionModel auctionModel = new AuctionModel("test auction", 45.0, 55.00, "test description", LocalDateTime.now(), new SellerModel(),new CategoryModel("Moto"));
    }
    @Test
    void shouldReturnListOfAuctionsForExistingCategory(@Autowired WebTestClient testClient) {
        List responseBody = testClient
                .get()
                .uri("/auction/searchByCategory?category=moto")
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();

        assertEquals(2, responseBody.size());
    }
    @Test
    void shouldReturnEmptyListForCategoryWithoutAuctions(@Autowired WebTestClient testClient) {
        List responseBody = testClient
                .get()
                .uri("/auction/searchByCategory?category=tools")
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class).returnResult().getResponseBody();

        assertEquals(0, responseBody.size());
    }
    @Test
    void shouldReturn400ForNonExistingCategory(@Autowired WebTestClient testClient) {
        testClient
                .get()
                .uri("/auction/searchByCategory?category=wrongCategory")
                .headers(headersConsumer -> headersConsumer.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().isBadRequest();
    }
}