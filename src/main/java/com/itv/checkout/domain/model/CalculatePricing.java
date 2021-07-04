package com.itv.checkout.domain.model;

public interface CalculatePricing {
    /**
     * Calculates a UnitCountPricing for a requestedUnits of items.
     * @param requestedUnits the number of items to price using this rule
     * @return UnitCountPricing containing information about the number of units this rule was applicable for
     * and the summed price for those units covered by this calculation.
     */
    UnitPricing calculatePricingFor(final int requestedUnits);
}
