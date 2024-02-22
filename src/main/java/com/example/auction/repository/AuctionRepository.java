package com.example.auction.repository;

import com.example.auction.model.AuctionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<AuctionModel, Integer> {
}
