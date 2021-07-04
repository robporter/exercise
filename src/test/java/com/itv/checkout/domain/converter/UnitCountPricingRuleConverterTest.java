package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.UnitCountPricing;
import com.itv.checkout.domain.model.rule.UnitCountPricingRule;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitCountPricingRuleConverterTest {

    private UnitCountPricingRuleConverter underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new UnitCountPricingRuleConverter();
    }

    @Test
    void toEntity() {
        final UnitCountPricing unitCountPricing = new UnitCountPricing(3, 2);

        final UnitCountPricingRuleEntity actual = underTest.toEntity("A", unitCountPricing);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new UnitCountPricingRuleEntity(
                        "A",
                        unitCountPricing.getEligibleUnits(),
                        unitCountPricing.getSummedPriceInPence()
                )
        );
    }

    @Test
    void toDomain() {
        final UnitCountPricingRuleEntity unitCountPricingRuleEntity = new UnitCountPricingRuleEntity(
                "A",
                2, 3
        );

        final UnitCountPricingRule actual = underTest.toDomain(unitCountPricingRuleEntity);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new UnitCountPricingRule(
                        unitCountPricingRuleEntity.getUnitCount(), unitCountPricingRuleEntity.getPriceInPence()
                )
        );
    }
}