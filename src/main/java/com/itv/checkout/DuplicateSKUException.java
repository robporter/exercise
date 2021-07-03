package com.itv.checkout;

public class DuplicateSKUException extends RuntimeException {

    public DuplicateSKUException() {
        super("Duplicate SKU");
    }
}
