package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;

import java.util.Objects;

public class PricingImpl implements Pricing {

    final int eligibleUnits;
    final int priceInPence;

    public PricingImpl(final int eligibleUnits,
                       final int priceInPence) {
        this.eligibleUnits = eligibleUnits;
        this.priceInPence = priceInPence;
    }

    @Override
    public int eligibleUnits() {
        return eligibleUnits;
    }

    @Override
    public int priceInPence() {
        return priceInPence;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PricingImpl pricing = (PricingImpl) o;
        return eligibleUnits == pricing.eligibleUnits && priceInPence == pricing.priceInPence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eligibleUnits, priceInPence);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PricingImpl{");
        sb.append("eligibleUnits=").append(eligibleUnits);
        sb.append(", priceInPence=").append(priceInPence);
        sb.append('}');
        return sb.toString();
    }
}
