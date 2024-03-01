package com.example.auction.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class AuctionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "999999.99")
    private Double initialPrice;

    @NotNull
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "999999.99")
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double currentPrice;

    @NotEmpty
    private String description;

    @Future
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerModel seller;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    public AuctionModel(String name, Double initialPrice, Double currentPrice, String description, LocalDateTime endTime) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.endTime = endTime;
    }
    public AuctionModel(String name, Double initialPrice, Double currentPrice, String description, LocalDateTime endTime, SellerModel seller) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.endTime = endTime;
        this.seller = seller; // Przypisanie sprzedawcy do aukcji podczas tworzenia nowej aukcji
    }
    public AuctionModel() {
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

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
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

    public SellerModel getSeller() {
        return seller;
    }

    public void setSeller(SellerModel seller) {
        this.seller = seller;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}
