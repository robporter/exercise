package com.itv.checkout.domain.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents details of a price for a quantity of units.
 */
public class Pricing {

    final int eligibleUnits;
    final int priceInPence;

    public Pricing(final int eligibleUnits,
                   final int summedPriceInPence) {
        this.eligibleUnits = eligibleUnits;
        this.priceInPence = summedPriceInPence;
    }

    public int getEligibleUnits() {
        return eligibleUnits;
    }

    public int getSummedPriceInPence() {
        return priceInPence;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
