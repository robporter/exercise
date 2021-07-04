package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.entity.SkuEntity;

public class SkuConverter {

    public SkuEntity toEntity(final Sku sku) {
        return new SkuEntity(
                sku.getCode()
        );
    }

}
