package com.itv.checkout.domain.exception;

public class DuplicateSKUException extends RuntimeException {

    public DuplicateSKUException() {
        super("Duplicate SKU");
    }
}
