package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.entity.SkuEntity;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

import java.util.Arrays;

public class SkuConverter {

    public SkuEntity toEntity(final Sku sku) {
        return new SkuEntity(
                sku.getCode(),
                Arrays.asList(new UnitCountPricingRuleEntity(
                        sku.getPriceInPence(),
                        1
                ))
        );
    }

}
