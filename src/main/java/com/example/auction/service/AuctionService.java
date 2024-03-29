package com.example.auction.service;

import com.example.auction.CategoryNotFoundException;
import com.example.auction.model.AuctionModel;
import com.example.auction.model.CategoryModel;
import com.example.auction.repository.AuctionRepository;
import com.example.auction.repository.CategoryRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {
    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    public AuctionService(AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<AuctionModel> getAuctionList() {
        return auctionRepository.findAll();
    }

    public List<AuctionModel> getAuctionByCategory(String categoryName) {
        return auctionRepository
                .findByCategory(getCategoryByNameOrElseThrowException(categoryName));
    }

    private CategoryModel getCategoryByNameOrElseThrowException(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category " + categoryName + " not exist"));
    }


    public AuctionModel saveAuction(AuctionModel auction) {
        return categoryRepository.findByName(auction.getCategory().getName())
                .map(categoryModel -> setCategoryForAuctionAndSaveToRepo(auction, categoryModel))
                .orElseThrow(() -> new CategoryNotFoundException("Category " + auction.getCategory().getName() + " not found"));
    }

    //        Optional<CategoryModel> categoryByName = categoryRepository.findByName(modelAuction.getCategory().getName());
//        CategoryModel categoryNotFound = categoryByName
//                .orElseThrow(() -> new ObjectNotFoundException(modelAuction, "category not found"));
//        return getAuctionModel(modelAuction, categoryNotFound);
    //Ta powyższa metoda została zrobiona na początku, działa tak samo jak poniżej
    private AuctionModel setCategoryForAuctionAndSaveToRepo(AuctionModel auction, CategoryModel categoryModel) {
        auction.setCategory(categoryModel);
        return auctionRepository.save(auction);
    }

    public AuctionModel updateAuction(AuctionModel auction, Long id) {
        return auctionRepository.save(findAuctionByIdAndUpdateFields(id, auction));
    }

    private AuctionModel findAuctionByIdAndUpdateFields(Long id, AuctionModel updateObject) {
        return auctionRepository.findById(id)
                .map(auctionFromRepo -> updateAuction(updateObject, auctionFromRepo))
                .orElseThrow(() -> new ObjectNotFoundException(id, " auction not found"));
    }

    private AuctionModel updateAuction(AuctionModel updateObject, AuctionModel auctionFromRepo) {
        auctionFromRepo.setName(updateObject.getName());
        auctionFromRepo.setCategory(updateObject.getCategory());
        auctionFromRepo.setCurrentPrice(updateObject.getCurrentPrice());
        auctionFromRepo.setInitialPrice(updateObject.getInitialPrice());
        auctionFromRepo.setDescription(updateObject.getDescription());
        auctionFromRepo.setEndTime(updateObject.getEndTime());
        return auctionFromRepo;
    }

    public ResponseEntity<Object> deleteAuction(Long id) {
        return auctionRepository.findById(id)
                .map(auctionModel -> {
                    auctionRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
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
    public void save(AuctionModel auctionModel) {
    }
}
