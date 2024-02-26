package com.example.auction.controller;

import com.example.auction.model.SellerModel;
import com.example.auction.repository.SellerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellerController {
    private final SellerRepository sellerRepository;

    public SellerController(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @PostMapping
    public SellerModel CreateSeller(@RequestBody SellerModel sellerModel) {
        return sellerRepository.save(sellerModel);
    }

}
