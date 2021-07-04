package com.itv.checkout.persistence.entity;

public class UnitCountPricingRuleEntity {

    private final int priceInPence;
    private final int forQuantityOfUnits;

    public UnitCountPricingRuleEntity(final int priceInPence,
                                      final int forQuantityOfUnits) {
        this.priceInPence = priceInPence;
        this.forQuantityOfUnits = forQuantityOfUnits;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    public int getForQuantityOfUnits() {
        return forQuantityOfUnits;
    }
}
