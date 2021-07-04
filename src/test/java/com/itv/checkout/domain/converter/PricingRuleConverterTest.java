package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.rule.UnitCountPricingRule;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PricingRuleConverterTest {

    private PricingRuleConverter underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new PricingRuleConverter();
    }

    @Test
    void toEntity() {
        final UnitCountPricingRule unitCountPricingRule = new UnitCountPricingRule(1, 2);

        final UnitCountPricingRuleEntity actual = underTest.toEntity(unitCountPricingRule);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new UnitCountPricingRuleEntity(
                        unitCountPricingRule.getPriceInPence(),
                        unitCountPricingRule.getUnitCount()
                ));
    }
}