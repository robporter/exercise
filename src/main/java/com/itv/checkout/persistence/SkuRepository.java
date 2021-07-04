package com.itv.checkout.persistence;

import com.itv.checkout.persistence.entity.SkuEntity;

import java.util.Optional;

public interface SkuRepository {

    void store(final SkuEntity skuEntity);

    Optional<SkuEntity> findSkuByCode(final String code);
}
