package com.itv.checkout.domain.service;

import com.itv.checkout.domain.converter.PricingRuleConverter;
import com.itv.checkout.domain.converter.SkuConverter;
import com.itv.checkout.domain.model.CartItem;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.domain.model.UnitPricing;
import com.itv.checkout.persistence.PricingRuleRepository;
import com.itv.checkout.persistence.SkuRepository;
import com.itv.checkout.persistence.entity.PricingRuleEntity;
import com.itv.checkout.persistence.entity.SkuEntity;
import com.itv.checkout.persistence.repository.InMemoryPricingRuleRepository;
import com.itv.checkout.persistence.repository.InMemorySkuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PricingCalculatorServiceIntegrationTest {

    private PricingCalculatorService underTest;

    private InventoryService inventoryService;

    @BeforeEach
    void beforeEach() {
        final List<SkuEntity> skuInventory = new ArrayList<>();
        final Map<String, List<PricingRuleEntity>> rulesInventory = new HashMap<>();
        final PricingRuleRepository pricingRuleRepository = new InMemoryPricingRuleRepository(rulesInventory);
        final SkuRepository skuRepository = new InMemorySkuRepository(skuInventory);
        final SkuConverter skuConverter = new SkuConverter();
        final PricingRuleConverter pricingRuleConverter = new PricingRuleConverter();
        inventoryService = new InventoryService(
                skuRepository,
                skuConverter,
                pricingRuleConverter,
                pricingRuleRepository
        );
        underTest = new PricingCalculatorService(pricingRuleRepository, pricingRuleConverter);
    }

    @Test
    void totalUsesDefaultItemPriceWhenThereAreNoOtherPricingRules() {

        inventoryService.addSku(new Sku("A", 100));
        inventoryService.addSku(new Sku("B", 200));
        final List<CartItem> items = Arrays.asList(
                new CartItem("A", 1),
                new CartItem("B", 2)
        );

        final int actual = underTest.total(items);

        assertThat(actual).isEqualTo(500);
    }

    @Test
    void totalUsesApplicablePricingRule() {

        inventoryService.addSku(new Sku("A", 100));
        inventoryService.addSku(new Sku("B", 200));
        inventoryService.addPricingRule("A", new UnitPricing(30, 80));

        final List<CartItem> items = Arrays.asList(
                new CartItem("A", 30),
                new CartItem("B", 2)
        );

        final int actual = underTest.total(items);

        assertThat(actual).isEqualTo(480);
    }

    @Test
    void usesAllRulesToGiveBestValue() {

        inventoryService.addSku(new Sku("A", 100));
        inventoryService.addSku(new Sku("B", 200));
        inventoryService.addPricingRule("A", new UnitPricing(30, 80));
        inventoryService.addPricingRule("A", new UnitPricing(10, 90));

        final List<CartItem> items = Arrays.asList(
                new CartItem("A", 40),
                new CartItem("B", 2)
        );

        final int actual = underTest.total(items);

        assertThat(actual).isEqualTo(570);
    }

    @Test
    void usesAllRulesAndDefaultPriceToGiveBestValue() {

        inventoryService.addSku(new Sku("A", 100));
        inventoryService.addSku(new Sku("B", 200));
        inventoryService.addPricingRule("A", new UnitPricing(24, 80));
        inventoryService.addPricingRule("A", new UnitPricing(7, 91));

        final List<CartItem> items = Arrays.asList(
                new CartItem("A", 40),
                new CartItem("B", 2)
        );

        final int actual = underTest.total(items);

        assertThat(actual).isEqualTo(862);
    }
}