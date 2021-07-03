package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.PricingRule;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Prices the requested units based on how many times they can be evenly divided by the eligibleQuantity
 * and multiplies that value by the pricePerEligibleQuantity.<br>
 * Any remaining requested units are not included in the eligibleUnits or price value of the returned Pricing.
 */
public class UnitCountPricingRule implements PricingRule {

    private final int forQuantityOfUnits;
    private final int priceInPence;

    public UnitCountPricingRule(final int forQuantityOfUnits,
                                final int priceInPence) {
        this.forQuantityOfUnits = forQuantityOfUnits;
        this.priceInPence = priceInPence;
    }

    @Override
    public Pricing getPricing(final int requestedUnits) {
        if (forQuantityOfUnits == 0) {
            return new PricingImpl(0, 0);
        }
        final int ineligibleUnits = requestedUnits % forQuantityOfUnits;
        final int eligibleUnits = requestedUnits - ineligibleUnits;
        final int priceInPence = this.priceInPence * eligibleUnits / forQuantityOfUnits;
        return new PricingImpl(eligibleUnits, priceInPence);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
