package com.itv.checkout.domain.service;

import com.itv.checkout.domain.converter.SkuConverter;
import com.itv.checkout.domain.exception.DuplicateSKUException;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.InventoryRepository;

public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SkuConverter skuConverter;

    public InventoryService(final InventoryRepository inventoryRepository,
                            final SkuConverter skuConverter) {
        this.inventoryRepository = inventoryRepository;
        this.skuConverter = skuConverter;
    }

    public void addSku(final Sku sku) {
        if (inventoryRepository.findSkuByCode(sku.getCode()).isPresent()) {
            throw new DuplicateSKUException();
        }
        inventoryRepository.store(skuConverter.toEntity(sku));
    }

    public void addUnitCountPricingRule(final String skuCode,
                                        final int unitQuantity,
                                        final int priceInPence) {
    }
}
