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
        final UnitCountPricingRule unitCountPricingRule = new UnitCountPricingRule(2, 3);

        final UnitCountPricingRuleEntity actual = underTest.toEntity("A", unitCountPricingRule);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new UnitCountPricingRuleEntity(
                        "A",
                        unitCountPricingRule.getPriceInPence(),
                        unitCountPricingRule.getUnitCount()
                )
        );
    }

    @Test
    void toDomain() {
        final UnitCountPricingRuleEntity unitCountPricingRuleEntity = new UnitCountPricingRuleEntity(
                "A",
                3,
                2
        );

        final UnitCountPricingRule actual = underTest.toDomain(unitCountPricingRuleEntity);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new UnitCountPricingRule(
                        unitCountPricingRuleEntity.getPriceInPence(),
                        unitCountPricingRuleEntity.getUnitCount()
                )
        );
    }
}