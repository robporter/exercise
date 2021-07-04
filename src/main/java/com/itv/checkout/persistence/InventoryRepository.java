package com.itv.checkout.persistence;

import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.entity.SkuEntity;

import java.util.Optional;

public interface InventoryRepository {

    void store(final SkuEntity skuEntity);

    Optional<Sku> findSkuByCode(final String code);
}
