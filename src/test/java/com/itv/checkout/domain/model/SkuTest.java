package com.itv.checkout.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SkuTest {

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C", "D", "E"})
    void getCodeReturnsSkuCode(String expectedSkuCode) {
        final Sku underTest = new Sku(expectedSkuCode, 2);

        final String actual = underTest.getCode();

        assertThat(actual).isEqualTo(expectedSkuCode);
    }
}