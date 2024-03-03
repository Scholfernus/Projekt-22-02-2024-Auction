package com.example.auction.repository;

import com.example.auction.model.AuctionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface AuctionRepository extends JpaRepository<AuctionModel, Long> {

}
