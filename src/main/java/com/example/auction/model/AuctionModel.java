package com.example.auction.model;

import com.example.auction.category.CategoryModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
public class AuctionModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotEmpty
private String name;
@Digits(integer = 6, fraction=2)
@DecimalMin(value = "0.01")
@DecimalMax(value = "999999.99")
private Integer initialPrice;
private Integer currentPrice;
private String description;
private LocalDateTime endTime;
@OneToMany(mappedBy = "auctions")
 private SellerModel seller;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryModel category;
    public AuctionModel() {
    }

    public AuctionModel(String name, Integer initialPrice, Integer currentPrice, String description, LocalDateTime endTime) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Integer currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "AuctionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialPrice=" + initialPrice +
                ", currentPrice=" + currentPrice +
                ", description='" + description + '\'' +
                ", endTime=" + endTime +
                '}';
    }
}
