package com.itv.checkout.domain.model;

public interface PricingRule {
    /**
     * Calculates a Pricing for a quantity of items.
     * @param quantity the number of items to price using this rule
     * @return Pricing containing the summed price for the number of eligible units covered by this rule,
     * and the number of units covered by this rule of those requested
     */
    Pricing getPricing(final int quantity);
}
