package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.entity.SkuEntity;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class SkuConverterTest {

    private SkuConverter underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new SkuConverter();
    }

    @ParameterizedTest(name = "[{index}] skuCode: {0}, priceInPence: {1}")
    @ArgumentsSource(SkuFixtures.class)
    void toEntity(final String skuCode,
                  final int priceInPence) {

        final Sku sku = new Sku(skuCode, priceInPence);

        final SkuEntity actual = underTest.toEntity(sku);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new SkuEntity(
                        sku.getCode(),
                        asList(new UnitCountPricingRuleEntity(
                                sku.getPriceInPence(),
                                1
                        ))
                )
        );
    }

    private static class SkuFixtures implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("A", 2),
                Arguments.of("B", 3)
        );
    }
}
}