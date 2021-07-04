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
}
