package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.UnitPricing;
import com.itv.checkout.domain.model.rule.PricingRule;
import com.itv.checkout.persistence.entity.PricingRuleEntity;
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
        final UnitPricing unitPricing = new UnitPricing(3, 2);

        final PricingRuleEntity actual = underTest.toEntity("A", unitPricing);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new PricingRuleEntity(
                        "A",
                        unitPricing.getEligibleUnits(),
                        unitPricing.getSummedPriceInPence()
                )
        );
    }

    @Test
    void toDomain() {
        final PricingRuleEntity pricingRuleEntity = new PricingRuleEntity(
                "A",
                2, 3
        );

        final PricingRule actual = underTest.toDomain(pricingRuleEntity);

        assertThat(actual).usingRecursiveComparison().isEqualTo(
                new PricingRule(
                        pricingRuleEntity.getUnitCount(), pricingRuleEntity.getPriceInPence()
                )
        );
    }
}