package com.ecom.inventory.service;

import com.ecom.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository=inventoryRepository;
    }

    public boolean isInStock(String skuCode,Integer quantity){

        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);

    }
}
