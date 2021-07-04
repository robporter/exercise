package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.UnitCountPricing;
import com.itv.checkout.domain.model.rule.UnitCountPricingRule;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

public class UnitCountPricingRuleConverter {

    public UnitCountPricingRuleEntity toEntity(final String skuCode,
                                               final UnitCountPricing unitCountPricing) {
        return new UnitCountPricingRuleEntity(
                skuCode,
                unitCountPricing.getEligibleUnits(),
                unitCountPricing.getSummedPriceInPence()
        );
    }

    public UnitCountPricingRule toDomain(final UnitCountPricingRuleEntity unitCountPricingRuleEntity) {
        return new UnitCountPricingRule(
                unitCountPricingRuleEntity.getUnitCount(),
                unitCountPricingRuleEntity.getPriceInPence()
        );
    }
}
