package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.entity.PricingRuleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryPricingRuleRepositoryTest {

    private InMemoryPricingRuleRepository underTest;
    private Map<String, List<PricingRuleEntity>> inventory;


    @BeforeEach
    void beforeEach() {
        inventory = new HashMap<>();
        underTest = new InMemoryPricingRuleRepository(inventory);
    }

    @Test
    void storesRuleForSku() {

        final PricingRuleEntity pricingRuleEntity = new PricingRuleEntity("A", 10, 100);

        underTest.store(pricingRuleEntity);

        assertThat(inventory).containsEntry("A", Collections.singletonList(pricingRuleEntity));
    }

    @Test
    void storesRulesForSameSku() {

        final PricingRuleEntity rule = new PricingRuleEntity("A", 10, 100);
        final PricingRuleEntity anotherRuleForSameSkuCode = new PricingRuleEntity("A", 40, 50);

        underTest.store(rule);
        underTest.store(anotherRuleForSameSkuCode);

        assertThat(inventory).containsEntry("A", Arrays.asList(rule, anotherRuleForSameSkuCode));
    }

    @Test
    void storeOverwriteRulesForSameSkuAndQuantity() {

        final PricingRuleEntity rule = new PricingRuleEntity("A", 10, 100);
        final PricingRuleEntity sameSkuRuleQuantityDifferentPrice = new PricingRuleEntity("A", 10, 50);

        underTest.store(rule);
        underTest.store(sameSkuRuleQuantityDifferentPrice);

        assertThat(inventory).containsEntry("A", Collections.singletonList(sameSkuRuleQuantityDifferentPrice));
    }

    @Test
    void findsStoreRulesBySkuCode() {
        final PricingRuleEntity rule = new PricingRuleEntity("A", 10, 100);
        final PricingRuleEntity anotherRuleForSameSkuCode = new PricingRuleEntity("A", 40, 50);
        final PricingRuleEntity anotherRuleForDifferentSkuCode = new PricingRuleEntity("B", 40, 50);
        underTest.store(rule);
        underTest.store(anotherRuleForSameSkuCode);
        underTest.store(anotherRuleForDifferentSkuCode);

        final List<PricingRuleEntity> actual = underTest.findRulesBySkuCode("A");

        assertThat(actual).usingRecursiveComparison().isEqualTo(Arrays.asList(rule, anotherRuleForSameSkuCode));
    }
}