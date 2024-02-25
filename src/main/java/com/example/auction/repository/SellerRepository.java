package com.example.auction.repository;

import com.example.auction.model.SellerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepository extends JpaRepository<SellerModel, Long> {

}
