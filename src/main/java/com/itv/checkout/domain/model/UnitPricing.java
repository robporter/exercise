package com.itv.checkout.domain.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represents details of a price for a quantity of units.
 */
public class UnitPricing {

    private final int eligibleUnits;
    private final int summedPriceInPence;

    public UnitPricing(final int eligibleUnits,
                       final int summedPriceInPence) {
        this.eligibleUnits = eligibleUnits;
        this.summedPriceInPence = summedPriceInPence;
    }

    public int getEligibleUnits() {
        return eligibleUnits;
    }

    public int getSummedPriceInPence() {
        return summedPriceInPence;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
