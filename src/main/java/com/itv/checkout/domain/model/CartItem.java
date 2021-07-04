package com.itv.checkout.domain.model;

public class CartItem {

    private final String skuCode;
    private int quantity;

    public CartItem(final String skuCode,
                    final int quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getSkuCode() {
        return skuCode;
    }
}
