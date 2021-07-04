package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.entity.SkuEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class SkuConverterTest {

    private SkuConverter underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new SkuConverter();
    }

    @Test
    void toEntity() {

        final Sku sku = new Sku("A");

        final SkuEntity actual = underTest.toEntity(sku);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new SkuEntity(
                        sku.getCode(),
                        Collections.emptyList()
                )
        );
    }
}