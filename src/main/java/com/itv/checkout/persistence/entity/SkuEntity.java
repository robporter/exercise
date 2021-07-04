package com.itv.checkout.persistence.entity;

import java.util.List;

public class SkuEntity {

    private final String code;
    private final List<UnitCountPricingRuleEntity> unitCountPricingRuleEntities;

    public SkuEntity(final String code,
                     final List<UnitCountPricingRuleEntity> unitCountPricingRuleEntities) {
        this.code = code;
        this.unitCountPricingRuleEntities = unitCountPricingRuleEntities;
    }

    public List<UnitCountPricingRuleEntity> getUnitCountPricingRuleEntities() {
        return unitCountPricingRuleEntities;
    }

    public String getCode() {
        return code;
    }
}
