package com.itv.checkout.domain.model;

public class Sku {

    private final String code;
    private final int priceInPence;

    public Sku(final String code,
               final int priceInPence) {
        this.code = code;
        this.priceInPence = priceInPence;
    }

    public String getCode() {
        return code;
    }

    public int getPriceInPence() {
        return priceInPence;
    }
}
