package com.itv.checkout.domain.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Pricing {

    final int eligibleUnits;
    final int priceInPence;

    public Pricing(final int eligibleUnits,
                   final int priceInPence) {
        this.eligibleUnits = eligibleUnits;
        this.priceInPence = priceInPence;
    }

    /**
     *
     * @return the number of units from those requested that are eligible for this price
     */
    public int eligibleUnits() {
        return eligibleUnits;
    }

    /**
     *
     * @return the summed price in pence that the eligible units are priced at
     */
    public int priceInPence() {
        return priceInPence;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
