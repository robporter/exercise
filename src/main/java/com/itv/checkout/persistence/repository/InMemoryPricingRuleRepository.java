package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.PricingRuleRepository;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InMemoryPricingRuleRepository implements PricingRuleRepository {

    private final Map<String, List<UnitCountPricingRuleEntity>> inventory;

    public InMemoryPricingRuleRepository(final Map<String, List<UnitCountPricingRuleEntity>> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void store(final UnitCountPricingRuleEntity unitCountPricingRuleEntity) {
        final List<UnitCountPricingRuleEntity> rules = findRulesForSkuCode(unitCountPricingRuleEntity.getSkuCode());
        rules.stream()
                .filter(entity -> entity.getUnitCount() == unitCountPricingRuleEntity.getUnitCount())
                .findAny()
                .ifPresent(rules::remove);
        rules.add(unitCountPricingRuleEntity);
        inventory.put(unitCountPricingRuleEntity.getSkuCode(), rules);
    }

    @Override
    public List<UnitCountPricingRuleEntity> findRulesBySkuCode(final String skuCode) {
        return Collections.unmodifiableList(findRulesForSkuCode(skuCode));
    }

    private List<UnitCountPricingRuleEntity> findRulesForSkuCode(final String skuCode) {
        final List<UnitCountPricingRuleEntity> entities = inventory.get(skuCode);
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities;
    }
}
