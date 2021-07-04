package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.rule.UnitCountPricingRule;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

public class UnitCountPricingRuleConverter {

    public UnitCountPricingRuleEntity toEntity(final String skuCode,
                                               final Pricing pricing) {
        return new UnitCountPricingRuleEntity(
                skuCode,
                pricing.getEligibleUnits(),
                pricing.getSummedPriceInPence()
        );
    }

    public UnitCountPricingRule toDomain(final UnitCountPricingRuleEntity unitCountPricingRuleEntity) {
        return new UnitCountPricingRule(
                unitCountPricingRuleEntity.getUnitCount(), unitCountPricingRuleEntity.getPriceInPence()
        );
    }
}
