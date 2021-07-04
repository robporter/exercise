package com.itv.checkout.persistence;

import com.itv.checkout.persistence.entity.PricingRuleEntity;

import java.util.List;

public interface PricingRuleRepository {
    void store(final PricingRuleEntity pricingRuleEntity);
    List<PricingRuleEntity> findRulesBySkuCode(final String skuCode);
}
