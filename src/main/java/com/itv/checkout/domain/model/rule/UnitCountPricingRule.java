package com.itv.checkout.domain.model.rule;

import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.PricingRule;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Prices the requested units based on how many times they can be evenly divided by the eligibleQuantity
 * and multiplies that value by the pricePerEligibleQuantity.<br>
 * Any remaining requested units, that are not evenly divisible, are not included in the eligibleUnits
 * or price value of the returned Pricing.
 */
public class UnitCountPricingRule implements PricingRule {

    private final int unitCount;
    private final int priceInPence;

    public UnitCountPricingRule(final int unitCount,
                                final int priceInPence) {
        this.unitCount = unitCount;
        this.priceInPence = priceInPence;
    }

    @Override
    public Pricing getPricingFor(final int requestedUnits) {
        if (unitCount == 0) {
            return new Pricing(0, 0);
        }
        final int ineligibleUnits = requestedUnits % unitCount;
        final int eligibleUnits = requestedUnits - ineligibleUnits;
        final int priceInPence = this.priceInPence * eligibleUnits / unitCount;
        return new Pricing(eligibleUnits, priceInPence);
    }

    public int getUnitCount() {
        return unitCount;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
