package com.itv.checkout.persistence;

import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

public interface PricingRuleRepository {
    void store(final UnitCountPricingRuleEntity unitCountPricingRuleEntity);
}
