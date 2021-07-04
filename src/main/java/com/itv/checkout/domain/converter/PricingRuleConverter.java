package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.rule.UnitCountPricingRule;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

public class PricingRuleConverter {

    public UnitCountPricingRuleEntity toEntity(final String skuCode,
                                               final UnitCountPricingRule unitCountPricingRule) {
        return new UnitCountPricingRuleEntity(
                skuCode, unitCountPricingRule.getPriceInPence(),
                unitCountPricingRule.getUnitCount()
        );
    }

    public UnitCountPricingRule toDomain(final UnitCountPricingRuleEntity unitCountPricingRuleEntity) {
        return new UnitCountPricingRule(
                unitCountPricingRuleEntity.getPriceInPence(),
                unitCountPricingRuleEntity.getUnitCount()
        );
    }
}
