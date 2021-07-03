package com.itv.checkout.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SkuTest {

    @Test
    void getCodeReturnsSkuCode() {
        final String expectedSkuCode = "A";
        final Sku underTest = new Sku(expectedSkuCode);

        final String actual = underTest.getCode();

        assertThat(actual).isEqualTo(expectedSkuCode);
    }
}