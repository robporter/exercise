package com.itv.checkout.persistence.entity;

public class UnitCountPricingRuleEntity {

    private final int priceInPence;
    private final int unitCount;

    public UnitCountPricingRuleEntity(final int priceInPence,
                                      final int unitCount) {
        this.priceInPence = priceInPence;
        this.unitCount = unitCount;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    public int getUnitCount() {
        return unitCount;
    }
}
