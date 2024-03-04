package com.example.auction.controller;

import com.example.auction.model.AuctionModel;
import com.example.auction.model.CategoryModel;
import com.example.auction.repository.AuctionRepository;
import com.example.auction.repository.CategoryRepository;
import com.example.auction.service.AuctionService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;
    private final AuctionService auctionService;

    public AuctionController(AuctionRepository auctionRepository, CategoryRepository categoryRepository, AuctionService auctionService) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
        this.auctionService = auctionService;
    }

    @PostMapping("/add")
    public AuctionModel postAuction(@RequestBody @Valid AuctionModel modelAuction) {
//        Optional<CategoryModel> categoryByName = categoryRepository.findByName(modelAuction.getCategory().getName());
//        CategoryModel categoryNotFound = categoryByName
//                .orElseThrow(() -> new ObjectNotFoundException(modelAuction, "category not found"));
//        return getAuctionModel(modelAuction, categoryNotFound);
        //Ta powyższa metoda została zrobiona na początku, działa tak samo jak poniżej

        return categoryRepository.findByName(modelAuction.getCategory().getName())
                .map(categoryNotFound -> getAuctionModel(modelAuction, categoryNotFound))
                .orElseThrow(() -> new ObjectNotFoundException(modelAuction.getCategory(), "category not found"));
    }

    private AuctionModel getAuctionModel(AuctionModel modelAuction, CategoryModel categoryNotFound) {
        modelAuction.setCategory(categoryNotFound);
        return auctionRepository.save(modelAuction);
    }

    @GetMapping()
    public List<AuctionModel> getAllAuction() {
        return auctionRepository.findAll();
    }

    @PutMapping("/{id}")
    public AuctionModel updateAuction (@PathVariable Long id, @RequestBody AuctionModel modelUpdate){

//        AuctionModel auctionNotFound = auctionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "auction not found"));
//        auctionNotFound.setName(modelUpdate.getName());
//        auctionNotFound.setCurrentPrice(modelUpdate.getCurrentPrice());
//        auctionNotFound.setInitialPrice(modelUpdate.getInitialPrice());
//        auctionNotFound.setDescription(modelUpdate.getDescription());
//        auctionNotFound.setEndTime(modelUpdate.getEndTime());
//        auctionNotFound.setSeller(modelUpdate.getSeller());
//        auctionNotFound.setCategory(modelUpdate.getCategory());
//        return auctionRepository.save(auctionNotFound);
        // to też jest poprawna wersja

        return auctionRepository.save(findByIdAndUpdateFields(id, modelUpdate));
    }

    private AuctionModel findByIdAndUpdateFields(Long id, AuctionModel modelUpdate) {
        return auctionRepository.findById(id)
                .map(auctionNotFound -> updatedAuction(modelUpdate, auctionNotFound))
                .orElseThrow(() -> new ObjectNotFoundException(id, "auction not found "));
    }

    private static AuctionModel updatedAuction(AuctionModel modelUpdate, AuctionModel auctionNotFound) {
        auctionNotFound.setName(modelUpdate.getName());
        auctionNotFound.setCurrentPrice(modelUpdate.getCurrentPrice());
        auctionNotFound.setInitialPrice(modelUpdate.getInitialPrice());
        auctionNotFound.setDescription(modelUpdate.getDescription());
        auctionNotFound.setEndTime(modelUpdate.getEndTime());
        auctionNotFound.setSeller(modelUpdate.getSeller());
        auctionNotFound.setCategory(modelUpdate.getCategory());
        return auctionNotFound;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuction(@PathVariable int id) {
        if (auctionRepository.findById((long) id).isPresent()) {
            auctionRepository.deleteById((long) id);
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

