package com.itv.checkout.domain.service;

import com.itv.checkout.domain.exception.DuplicateSKUException;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.InventoryRepository;

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

    public void addUnitCountPricingRule(final String skuCode,
                                        final int unitQuantity,
                                        final int priceInPence) {
    }
}
