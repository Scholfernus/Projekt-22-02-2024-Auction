package com.example.auction.service;

import com.example.auction.model.AuctionModel;
import com.example.auction.repository.AuctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }
    public List<AuctionModel> getAuctionList() {
        return auctionRepository.findAll();
    }

    public void save(AuctionModel auctionModel) {
        auctionRepository.save(auctionModel);
    }
}
