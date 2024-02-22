package com.example.auction.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuctionModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String name;

    public AuctionModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuctionModel() {
    }

    @Override
    public String toString() {
        return "AuctionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
