package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.PricingRule;

public class UnitCountPricingRule implements PricingRule {

    private final int eligibleQuantity;
    private final int pricePerEligibleQuantity;

    public UnitCountPricingRule(final int eligibleQuantity,
                                final int pricePerEligibleQuantity) {
        this.eligibleQuantity = eligibleQuantity;
        this.pricePerEligibleQuantity = pricePerEligibleQuantity;
    }

    @Override
    public Pricing getPricing(final int quantity) {
        return null;
    }
}
