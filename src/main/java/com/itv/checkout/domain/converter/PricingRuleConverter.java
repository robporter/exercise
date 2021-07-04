package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.rule.UnitCountPricingRule;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

public class PricingRuleConverter {

    public UnitCountPricingRuleEntity toEntity(final UnitCountPricingRule unitCountPricingRule) {
        return new UnitCountPricingRuleEntity(
                unitCountPricingRule.getPriceInPence(),
                unitCountPricingRule.getUnitCount()
        );
    }

    public UnitCountPricingRule toDomain(final UnitCountPricingRuleEntity unitCountPricingRuleEntity) {
        return new UnitCountPricingRule(
                unitCountPricingRuleEntity.getUnitCount(),
                unitCountPricingRuleEntity.getPriceInPence()
        );
    }
}
