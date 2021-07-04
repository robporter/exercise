package com.itv.checkout.domain.model;

public interface PricingRule {
    /**
     * Calculates a Pricing for a requestedUnits of items.
     * @param requestedUnits the number of items to price using this rule
     * @return Pricing containing information about the number of units this rule was applicable for
     * and the total price for those units covered by this rule
     */
    Pricing getPricingFor(final int requestedUnits);
}
