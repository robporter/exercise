package com.itv.checkout.domain.model.rule;

import com.itv.checkout.domain.model.UnitPricing;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PricingRuleTest {

    @ParameterizedTest(name = "[{index}] requestedUnits: {0}, rule: {1}, result: {2}")
    @ArgumentsSource(PriceFixtures.class)
    void getPricing(final int requestedUnits,
                    final PricingRule underTest,
                    final UnitPricing expectedPricing) {

        final UnitPricing actual = underTest.calculatePricingFor(requestedUnits);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPricing);
    }

    private static class PriceFixtures implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(1,
                                 new PricingRule(1, 2),
                                 new UnitPricing(1, 2)
                    ),
                    Arguments.of(2,
                                 new PricingRule(1, 3),
                                 new UnitPricing(2, 6)
                    ),
                    Arguments.of(3,
                                 new PricingRule(1, 11),
                                 new UnitPricing(3, 33)
                    ),
                    Arguments.of(3,
                                 new PricingRule(2, 10),
                                 new UnitPricing(2, 10)
                    ),
                    Arguments.of(5,
                                 new PricingRule(2, 10),
                                 new UnitPricing(4, 20)
                    ),
                    Arguments.of(6,
                                 new PricingRule(2, 10),
                                 new UnitPricing(6, 30)
                    ),
                    Arguments.of(0,
                                 new PricingRule(2, 10),
                                 new UnitPricing(0, 0)
                    ),
                    Arguments.of(10,
                                 new PricingRule(0, 10),
                                 new UnitPricing(0, 0)
                    )
            );
        }
    }
}