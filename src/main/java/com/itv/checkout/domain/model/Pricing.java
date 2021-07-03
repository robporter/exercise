package com.itv.checkout.domain.model;

public interface Pricing {

    /**
     *
     * @return the number of units from those requested that are eligible for this price
     */
    int eligibleUnits();

    /**
     *
     * @return the summed price in pence that the eligible units are priced at
     */
    int priceInPence();
}
