package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.repository.AuctionRepository;
import com.example.auction.service.AuctionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionRepository auctionRepository;
    private final AuctionService auctionService;

    public AuctionController(AuctionRepository auctionRepository, AuctionService auctionService) {
        this.auctionRepository = auctionRepository;
        this.auctionService = auctionService;
    }

    @PostMapping("/add")
    public AuctionModel postAuction(@RequestBody @Valid AuctionModel modelAuction) {
        return auctionRepository.save(modelAuction);
    }

    @GetMapping()
    public List<AuctionModel> getAllAuction() {
        return auctionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable int id) {
        if (auctionRepository.findById(id).isPresent()) {
            auctionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteAuction(@PathVariable int id) {
//        return auctionRepository.findById(id)
//                .map(auction -> {
//                    auctionRepository.deleteById(id);
//                    return ResponseEntity.noContent().build();
//                }).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//    inne rozwiązanie zadania delete

