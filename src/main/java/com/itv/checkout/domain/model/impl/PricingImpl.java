package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
