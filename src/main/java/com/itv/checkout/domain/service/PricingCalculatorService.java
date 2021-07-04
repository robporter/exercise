package com.itv.checkout.domain.service;

import com.itv.checkout.domain.converter.PricingRuleConverter;
import com.itv.checkout.domain.model.CartItem;
import com.itv.checkout.domain.model.UnitPricing;
import com.itv.checkout.domain.model.rule.PricingRule;
import com.itv.checkout.persistence.PricingRuleRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;


public class PricingCalculatorService {

    private final PricingRuleRepository pricingRuleRepository;
    private final PricingRuleConverter pricingRuleConverter;

    public PricingCalculatorService(final PricingRuleRepository pricingRuleRepository,
                                    final PricingRuleConverter pricingRuleConverter) {
        this.pricingRuleRepository = pricingRuleRepository;
        this.pricingRuleConverter = pricingRuleConverter;
    }

    public int total(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(item -> totalSku(getPricingRules(item), item.getQuantity()).stream()
                        .map(UnitPricing::getSummedPriceInPence)
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    private List<UnitPricing> totalSku(final List<PricingRule> pricingRules,
                                       final int quantity) {

        List<UnitPricing> bestPrices = new ArrayList<>();

        pricingRules.parallelStream()
                .map(rule -> rule.calculatePricingFor(quantity))
                .filter(pricing -> pricing.getEligibleUnits() != 0)
                .min(comparing(UnitPricing::getSummedPriceInPence)
                             .thenComparing(UnitPricing::getEligibleUnits)).ifPresent(pricing -> {
            bestPrices.add(pricing);
            if (pricing.getEligibleUnits() < quantity) {
                bestPrices.addAll(totalSku(pricingRules, quantity - pricing.getEligibleUnits()));
            }
        });

        return bestPrices;
    }

    private List<PricingRule> getPricingRules(final CartItem item) {
        return pricingRuleConverter.toDomain(
                pricingRuleRepository.findRulesBySkuCode(item.getSkuCode())
        );
    }
}
