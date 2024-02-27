package com.example.auction.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SellerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;
    @NotNull
    @NotEmpty
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_auction",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id"))
    private Set<AuctionModel> auction = new HashSet<>();

    public SellerModel(String name, Set<AuctionModel> auction) {
        this.name = name;
        this.auction = auction;
    }

    public SellerModel() {
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuctionModel> getAuction() {
        return auction;
    }

    public void setAuction(Set<AuctionModel> auction) {
        this.auction = auction;
    }
    public Set<AuctionModel> getAuctions() {
        return auction;
    }


}
