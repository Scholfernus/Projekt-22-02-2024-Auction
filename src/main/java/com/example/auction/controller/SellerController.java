package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.SellerModel;
import com.example.auction.repository.SellerRepository;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {
    private final SellerRepository sellerRepository;

    public SellerController(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @PostMapping
    public SellerModel CreateSeller(@RequestBody @Valid SellerModel sellerModel) {
        return sellerRepository.save(sellerModel);
    }
    @GetMapping("/{id}/auction")
    public Iterable<AuctionModel> getAuctionsForSeller(@PathVariable Long id){
        //Find if seller exist - get auctions for seller
        //declarative style
        return sellerRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException(id," seller not found"))
                .getAuctions();
        // poniższy kod jest opcjonalny
//        Optional<SellerModel> sellerModel = sellerRepository.findById(id);
//        if(sellerModel.isPresent()){
//            SellerModel unwrapedSeller = sellerModel.get();
//            return unwrapedSeller.getAuctions();
//        }else {
//            throw new ObjectNotFoundException(id, "seller not found");
//        }
    }
}
