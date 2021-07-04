package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.entity.SkuEntity;

import java.util.Collections;

public class SkuConverter {
    public SkuEntity toEntity(final Sku sku) {
        return new SkuEntity(sku.getCode(), Collections.emptyList());
    }
}
