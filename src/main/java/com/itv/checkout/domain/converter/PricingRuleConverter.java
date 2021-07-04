package com.itv.checkout.domain.converter;

import com.itv.checkout.domain.model.UnitPricing;
import com.itv.checkout.domain.model.rule.PricingRule;
import com.itv.checkout.persistence.entity.PricingRuleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PricingRuleConverter {

    public PricingRuleEntity toEntity(final String skuCode,
                                      final UnitPricing unitPricing) {
        return new PricingRuleEntity(
                skuCode,
                unitPricing.getEligibleUnits(),
                unitPricing.getSummedPriceInPence()
        );
    }

    public PricingRule toDomain(final PricingRuleEntity pricingRuleEntity) {
        return new PricingRule(
                pricingRuleEntity.getUnitCount(),
                pricingRuleEntity.getPriceInPence()
        );
    }

    public List<PricingRule> toDomain(final List<PricingRuleEntity> unitCountPricingRuleEntities) {
        return unitCountPricingRuleEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
