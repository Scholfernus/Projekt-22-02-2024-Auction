package com.example.auction.repository;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AuctionRepository extends JpaRepository<AuctionModel, Long> {
List<AuctionModel> findByCategory(CategoryModel categoryModel);
}
