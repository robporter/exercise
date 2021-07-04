package com.itv.checkout.domain.service;

import com.itv.checkout.domain.converter.SkuConverter;
import com.itv.checkout.domain.converter.UnitCountPricingRuleConverter;
import com.itv.checkout.domain.exception.DuplicateSKUException;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.domain.model.UnitCountPricing;
import com.itv.checkout.persistence.PricingRuleRepository;
import com.itv.checkout.persistence.SkuRepository;

public class InventoryService {

    private final SkuRepository skuRepository;
    private final SkuConverter skuConverter;
    private final UnitCountPricingRuleConverter pricingRuleConverter;
    private final PricingRuleRepository pricingRuleRepository;

    public InventoryService(final SkuRepository skuRepository,
                            final SkuConverter skuConverter,
                            final UnitCountPricingRuleConverter pricingRuleConverter,
                            final PricingRuleRepository pricingRuleRepository) {
        this.skuRepository = skuRepository;
        this.skuConverter = skuConverter;
        this.pricingRuleConverter = pricingRuleConverter;
        this.pricingRuleRepository = pricingRuleRepository;
    }

    public void addSku(final Sku sku) {
        if (skuRepository.findSkuByCode(sku.getCode()).isPresent()) {
            throw new DuplicateSKUException();
        }
        pricingRuleRepository.store(pricingRuleConverter.toEntity(
                sku.getCode(),
                createPricingForOneUnit(sku)
        ));
        skuRepository.store(skuConverter.toEntity(sku));
    }

    public void addPricingRule(final String skuCode,
                               final UnitCountPricing unitCountPricing) {
        pricingRuleRepository.store(pricingRuleConverter.toEntity(skuCode, unitCountPricing));
    }

    private UnitCountPricing createPricingForOneUnit(final Sku sku) {
        return new UnitCountPricing(1, sku.getPriceInPence());
    }

}
