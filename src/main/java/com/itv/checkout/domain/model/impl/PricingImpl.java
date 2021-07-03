package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;

import java.util.StringJoiner;

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
    public String toString() {
        return new StringJoiner(", ", PricingImpl.class.getSimpleName() + "[", "]")
                .add("eligibleUnits=" + eligibleUnits)
                .add("priceInPence=" + priceInPence)
                .toString();
    }
}
