package com.itv.checkout.domain.repository;

import com.itv.checkout.domain.model.Sku;

import java.util.Optional;

public class InventoryRepository {
    public void store(final Sku sku) {

    }

    public Optional<Sku> findSkuByCode(final String code) {
        return Optional.empty();
    }
}
