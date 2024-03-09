package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.service.AuctionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/add")
    public AuctionModel postAuction(@RequestBody @Valid AuctionModel modelAuction) {
        return auctionService.saveAuction(modelAuction);
    }

    @GetMapping()
    public List<AuctionModel> getAllAuction() {
        return auctionService.getAuctionList();
    }

    @GetMapping("/searchByCategory")
    public List<AuctionModel> getAuctionCategory(@RequestParam("category") String categoryName) {

        return auctionService.getAuctionByCategory(categoryName);
    }

    @PutMapping("/{id}")
    public AuctionModel updateAuction(@PathVariable Long id, @RequestBody @Valid AuctionModel modelUpdate) {
        return auctionService.updateAuction(modelUpdate, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuction(@PathVariable Long id) {
        return auctionService.deleteAuction(id);
    }
}


