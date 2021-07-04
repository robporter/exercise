package com.itv.checkout.persistence.entity;

public class UnitCountPricingRuleEntity {

    private final String skuCode;
    private final int priceInPence;
    private final int unitCount;

    public UnitCountPricingRuleEntity(final String skuCode,
                                      final int priceInPence,
                                      final int unitCount) {
        this.skuCode = skuCode;
        this.priceInPence = priceInPence;
        this.unitCount = unitCount;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    public int getUnitCount() {
        return unitCount;
    }

    public String getSkuCode() {
        return skuCode;
    }
}
