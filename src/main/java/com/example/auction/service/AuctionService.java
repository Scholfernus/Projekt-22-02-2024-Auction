package com.example.auction.service;

import com.example.auction.CategoryNotFoundException;
import com.example.auction.model.AuctionModel;
import com.example.auction.model.CategoryModel;
import com.example.auction.repository.AuctionRepository;
import com.example.auction.repository.CategoryRepository;
import org.hibernate.ObjectNotFoundException;
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

    public void save(AuctionModel auctionModel) {
        auctionRepository.save(auctionModel);
    }
    AuctionModel saveAuction(AuctionModel auction){
        return categoryRepository.findByName(auction.getCategory().getName())
                .map(categoryModel -> setCategoryForAuctionAndSaveToRepo(auction, categoryModel))
                .orElseThrow(()->new CategoryNotFoundException("Category " + auction.getCategory().getName() + " not found"));
    }
    private AuctionModel setCategoryForAuctionAndSaveToRepo(AuctionModel auction, CategoryModel categoryModel){
        auction.setCategory(categoryModel);
        return auctionRepository.save(auction);
    }
    AuctionModel updateAuction(AuctionModel auction, Long id){
        return auctionRepository.save(findAuctionByIdAndUpdateFields(id, auction));
    }
    private AuctionModel findAuctionByIdAndUpdateFields(Long id, AuctionModel updateObject){
        return auctionRepository.findById(id)
                .map(auctionFromRepo ->updateAuction(updateObject, auctionFromRepo))
                .orElseThrow(()-> new ObjectNotFoundException(id, " auction not found"));
    }
    private AuctionModel
}
