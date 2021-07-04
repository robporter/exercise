package com.itv.checkout.domain.service;

import com.itv.checkout.domain.converter.SkuConverter;
import com.itv.checkout.domain.exception.DuplicateSKUException;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.SkuRepository;

public class InventoryService {

    private final SkuRepository skuRepository;
    private final SkuConverter skuConverter;

    public InventoryService(final SkuRepository skuRepository,
                            final SkuConverter skuConverter) {
        this.skuRepository = skuRepository;
        this.skuConverter = skuConverter;
    }

    public void addSku(final Sku sku) {
        if (skuRepository.findSkuByCode(sku.getCode()).isPresent()) {
            throw new DuplicateSKUException();
        }
        skuRepository.store(skuConverter.toEntity(sku));
    }

    public void addUnitCountPricingRule(final String skuCode,
                                        final int unitQuantity,
                                        final int priceInPence) {
    }
}
