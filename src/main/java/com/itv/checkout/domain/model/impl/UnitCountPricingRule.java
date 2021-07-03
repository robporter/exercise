package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.PricingRule;

/**
 * Prices the requested units based on how many times they can be evenly divided by the eligibleQuantity
 * and multiplies that value by the pricePerEligibleQuantity.<br>
 * Any remaining requested units are not included in the eligibleUnits of the returned Pricing.
 */
public class UnitCountPricingRule implements PricingRule {

    private final int eligibleQuantity;
    private final int pricePerEligibleQuantity;

    public UnitCountPricingRule(final int eligibleQuantity,
                                final int pricePerEligibleQuantity) {
        this.eligibleQuantity = eligibleQuantity;
        this.pricePerEligibleQuantity = pricePerEligibleQuantity;
    }

    @Override
    public Pricing getPricing(final int requestedUnits) {
        final int ineligibleUnits = requestedUnits % eligibleQuantity;
        final int eligibleUnits = requestedUnits - ineligibleUnits;
        final int priceInPence = pricePerEligibleQuantity * eligibleUnits / eligibleQuantity;
        return new PricingImpl(eligibleUnits, priceInPence);
    }
}
