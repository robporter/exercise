package com.itv.checkout.domain.model.impl;

import com.itv.checkout.domain.model.Pricing;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UnitCountPricingRuleTest {

    @ParameterizedTest
    @ArgumentsSource(PriceFixtures.class)
    void getPricing(final int requestedQuantity,
                    final UnitCountPricingRule underTest,
                    final Pricing expectedPricing) {

        final Pricing actual = underTest.getPricing(requestedQuantity);

        assertThat(actual).isEqualTo(expectedPricing);
    }

    private static class PriceFixtures implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(1,
                                 new UnitCountPricingRule(1, 10),
                                 new PricingImpl(1, 10)
                    ),
                    Arguments.of(2,
                                 new UnitCountPricingRule(1, 10),
                                 new PricingImpl(2, 20)
                    ),
                    Arguments.of(3,
                                 new UnitCountPricingRule(1, 11),
                                 new PricingImpl(3, 33)
                    ),
                    Arguments.of(3,
                                 new UnitCountPricingRule(2, 10),
                                 new PricingImpl(2, 10)
                    )
            );
        }
    }
}