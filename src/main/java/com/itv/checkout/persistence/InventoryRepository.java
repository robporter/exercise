package com.itv.checkout.persistence;

import com.itv.checkout.domain.model.Sku;

import java.util.Optional;

public interface InventoryRepository {

    void store(final Sku sku);

    Optional<Sku> findSkuByCode(final String code);
}
