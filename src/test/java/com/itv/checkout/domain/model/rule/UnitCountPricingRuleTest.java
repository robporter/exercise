package com.itv.checkout.domain.model.rule;

import com.itv.checkout.domain.model.Pricing;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class UnitCountPricingRuleTest {

    @ParameterizedTest(name = "[{index}] requestedUnits: {0}, rule: {1}, result: {2}")
    @ArgumentsSource(PriceFixtures.class)
    void getPricing(final int requestedUnits,
                    final UnitCountPricingRule underTest,
                    final Pricing expectedPricing) {

        final Pricing actual = underTest.getPricing(requestedUnits);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedPricing);
    }

    private static class PriceFixtures implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(1,
                                 new UnitCountPricingRule(1, 2),
                                 new Pricing(1, 2)
                    ),
                    Arguments.of(2,
                                 new UnitCountPricingRule(1, 3),
                                 new Pricing(2, 6)
                    ),
                    Arguments.of(3,
                                 new UnitCountPricingRule(1, 11),
                                 new Pricing(3, 33)
                    ),
                    Arguments.of(3,
                                 new UnitCountPricingRule(2, 10),
                                 new Pricing(2, 10)
                    ),
                    Arguments.of(5,
                                 new UnitCountPricingRule(2, 10),
                                 new Pricing(4, 20)
                    ),
                    Arguments.of(6,
                                 new UnitCountPricingRule(2, 10),
                                 new Pricing(6, 30)
                    ),
                    Arguments.of(0,
                                 new UnitCountPricingRule(2, 10),
                                 new Pricing(0, 0)
                    ),
                    Arguments.of(10,
                                 new UnitCountPricingRule(0, 10),
                                 new Pricing(0, 0)
                    )
            );
        }
    }
}