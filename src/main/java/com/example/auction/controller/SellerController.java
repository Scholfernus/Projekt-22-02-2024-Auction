package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.SellerModel;
import com.example.auction.repository.AuctionRepository;
import com.example.auction.repository.SellerRepository;
import com.example.auction.service.AuctionService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerRepository sellerRepository;
    private final AuctionRepository auctionRepository;

    public SellerController(SellerRepository sellerRepository, AuctionRepository auctionRepository, AuctionService auctionService) {
        this.sellerRepository = sellerRepository;
        this.auctionRepository = auctionRepository;
        this.auctionService = auctionService;
    }
    private final AuctionService auctionService;


    @PostMapping
    public SellerModel createSeller(@RequestBody @Valid SellerModel sellerModel) {
        return sellerRepository.save(sellerModel);
    }

    @GetMapping("/{id}/auctions")
    public Iterable<AuctionModel> getAuctionsForSeller(@PathVariable Long id) {
        //Find if seller exist - get auctions for seller
        //declarative style
        return sellerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, " seller not found"))
                .getAuctions();
    }
//    @PostMapping("/{sellerId}/auctions")
//    public ResponseEntity<AuctionModel> createAuctionForSeller(
//            @PathVariable Long sellerId,
//            @RequestBody @Valid AuctionModel auctionModel) {
//        return sellerRepository.findById(sellerId)
//                .map(seller -> {
//                    auctionModel.setSeller(seller);
//                    AuctionModel savedAuction = auctionRepository.save(auctionModel);
//                    return ResponseEntity.created(URI.create("/sellers/" + sellerId + "/auctions/" + savedAuction.getId()))
//                            .body(savedAuction);
//                })
//                .orElse(ResponseEntity.notFound().build());
//        //Napisane przez chatGPT
//    }
    @PostMapping("/{sellerId}/auctions")
    public ResponseEntity<String> addAuctionToSeller(@PathVariable Long sellerId, @RequestBody @Valid AuctionModel auctionModel) {
        SellerModel seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ObjectNotFoundException(sellerId, "Seller not found"));

        auctionModel.setSeller(seller); // Ustawienie właściciela dla aukcji
        auctionService.save(auctionModel); // Zapisanie aukcji

        return ResponseEntity.status(HttpStatus.CREATED).body("Auction added to seller successfully");
    }
}
        // poniższy kod jest opcjonalny
//        Optional<SellerModel> sellerModel = sellerRepository.findById(id);
//        if(sellerModel.isPresent()){
//            SellerModel unwrapedSeller = sellerModel.get();
//            return unwrapedSeller.getAuctions();
//        }else {
//            throw new ObjectNotFoundException(id, "seller not found");
//        }

