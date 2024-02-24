package com.example.auction.model;

import com.example.auction.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
public class AuctionModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@NonNull
@NotEmpty
private String name;
@NonNull
@Digits(integer = 6, fraction=2)
@DecimalMin(value = "0.01")
@DecimalMax(value = "999999.99")
private BigDecimal initialPrice;
private BigDecimal currentPrice;
private String description;
private LocalDateTime endTime;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    public AuctionModel() {
    }

    public AuctionModel(String name, BigDecimal initialPrice, BigDecimal currentPrice, String description, LocalDateTime endTime) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
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
