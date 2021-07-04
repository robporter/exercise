package com.itv.checkout.persistence.entity;

public class UnitCountPricingRuleEntity {

    private final String skuCode;
    private final int priceInPence;
    private final int unitCount;

    public UnitCountPricingRuleEntity(final String skuCode,
                                      final int eligibleUnits,
                                      final int summedPriceInPence) {
        this.skuCode = skuCode;
        this.priceInPence = summedPriceInPence;
        this.unitCount = eligibleUnits;
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
