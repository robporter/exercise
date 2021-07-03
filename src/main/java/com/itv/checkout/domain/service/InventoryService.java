package com.itv.checkout.domain.service;

import com.itv.checkout.domain.exception.DuplicateSKUException;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.domain.repository.InventoryRepository;

import java.util.Optional;

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addSku(final Sku sku) {
        if (inventoryRepository.findSkuByCode(sku.getCode()).isPresent()) {
            throw new DuplicateSKUException();
        }
        inventoryRepository.store(sku);
    }

    public Optional<Sku> findSkuByCode(final String code) {
        return inventoryRepository.findSkuByCode(code);
    }
}
