package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.repository.AuctionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionRepository auctionRepository;

    public AuctionController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @PostMapping
    public AuctionModel postAuction(@RequestBody AuctionModel modelAuction) {
        return auctionRepository.save(modelAuction);
    }

    @GetMapping("/auction")
    public List<AuctionModel> getAllAuction() {
        return auctionRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable int id) {
        if (auctionRepository.findById(id).isPresent()) {
            auctionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
}
