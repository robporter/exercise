package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.PricingRule;

import java.util.StringJoiner;

/**
 * Prices the requested units based on how many times they can be evenly divided by the eligibleQuantity
 * and multiplies that value by the pricePerEligibleQuantity.<br>
 * Any remaining requested units are not included in the eligibleUnits or price value of the returned Pricing.
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
        if (eligibleQuantity == 0) {
            return new PricingImpl(0, 0);
        }
        final int ineligibleUnits = requestedUnits % eligibleQuantity;
        final int eligibleUnits = requestedUnits - ineligibleUnits;
        final int priceInPence = pricePerEligibleQuantity * eligibleUnits / eligibleQuantity;
        return new PricingImpl(eligibleUnits, priceInPence);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UnitCountPricingRule.class.getSimpleName() + "[", "]")
                .add("eligibleQuantity=" + eligibleQuantity)
                .add("pricePerEligibleQuantity=" + pricePerEligibleQuantity)
                .toString();
    }
}
