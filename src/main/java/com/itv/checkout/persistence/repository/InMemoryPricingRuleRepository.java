package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.PricingRuleRepository;
import com.itv.checkout.persistence.entity.PricingRuleEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InMemoryPricingRuleRepository implements PricingRuleRepository {

    private final Map<String, List<PricingRuleEntity>> inventory;

    public InMemoryPricingRuleRepository(final Map<String, List<PricingRuleEntity>> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void store(final PricingRuleEntity pricingRuleEntity) {
        final List<PricingRuleEntity> rules = findRulesForSkuCode(pricingRuleEntity.getSkuCode());
        rules.stream()
                .filter(entity -> entity.getUnitCount() == pricingRuleEntity.getUnitCount())
                .findAny()
                .ifPresent(rules::remove);
        rules.add(pricingRuleEntity);
        inventory.put(pricingRuleEntity.getSkuCode(), rules);
    }

    @Override
    public List<PricingRuleEntity> findRulesBySkuCode(final String skuCode) {
        return Collections.unmodifiableList(findRulesForSkuCode(skuCode));
    }

    private List<PricingRuleEntity> findRulesForSkuCode(final String skuCode) {
        final List<PricingRuleEntity> entities = inventory.get(skuCode);
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities;
    }
}
