package com.itv.checkout.persistence;

import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

import java.util.List;

public interface PricingRuleRepository {
    void store(final UnitCountPricingRuleEntity unitCountPricingRuleEntity);
    List<UnitCountPricingRuleEntity> findRulesBySkuCode(final String skuCode);
}
