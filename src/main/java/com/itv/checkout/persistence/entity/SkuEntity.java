package com.itv.checkout.persistence.entity;

import java.util.List;

public class SkuEntity {

    public final String code;
    public final List<UnitCountPricingRuleEntity> unitCountPricingRuleEntities;

    public SkuEntity(final String code,
                     final List<UnitCountPricingRuleEntity> unitCountPricingRuleEntities) {
        this.code = code;
        this.unitCountPricingRuleEntities = unitCountPricingRuleEntities;
    }

    public String getCode() {
        return code;
    }
}
