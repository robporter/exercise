package com.itv.checkout.domain.model;

public interface CalculatePricing {
    /**
     * Calculates a Pricing for a requestedUnits of items.
     * @param requestedUnits the number of items to price using this rule
     * @return Pricing containing information about the number of units this rule was applicable for
     * and the summed price for those units covered by this calculation.
     */
    Pricing calculatePricingFor(final int requestedUnits);
}
