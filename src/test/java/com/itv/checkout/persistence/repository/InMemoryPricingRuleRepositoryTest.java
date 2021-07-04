package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;
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
    private Map<String, List<UnitCountPricingRuleEntity>> inventory;


    @BeforeEach
    void beforeEach() {
        inventory = new HashMap<>();
        underTest = new InMemoryPricingRuleRepository(inventory);
    }

    @Test
    void storesRuleForSku() {

        final UnitCountPricingRuleEntity unitCountPricingRuleEntity = new UnitCountPricingRuleEntity("A", 10, 100);

        underTest.store(unitCountPricingRuleEntity);

        assertThat(inventory).containsEntry("A", Collections.singletonList(unitCountPricingRuleEntity));
    }

    @Test
    void storesRulesForSameSku() {

        final UnitCountPricingRuleEntity rule = new UnitCountPricingRuleEntity("A", 10, 100);
        final UnitCountPricingRuleEntity anotherRuleForSameSkuCode = new UnitCountPricingRuleEntity("A", 40, 50);

        underTest.store(rule);
        underTest.store(anotherRuleForSameSkuCode);

        assertThat(inventory).containsEntry("A", Arrays.asList(rule, anotherRuleForSameSkuCode));
    }

    @Test
    void storeOverwriteRulesForSameSkuAndQuantity() {

        final UnitCountPricingRuleEntity rule = new UnitCountPricingRuleEntity("A", 10, 100);
        final UnitCountPricingRuleEntity sameSkuRuleQuantityDifferentPrice = new UnitCountPricingRuleEntity("A", 10, 50);

        underTest.store(rule);
        underTest.store(sameSkuRuleQuantityDifferentPrice);

        assertThat(inventory).containsEntry("A", Collections.singletonList(sameSkuRuleQuantityDifferentPrice));
    }

    @Test
    void findsStoreRulesBySkuCode() {
        final UnitCountPricingRuleEntity rule = new UnitCountPricingRuleEntity("A", 10, 100);
        final UnitCountPricingRuleEntity anotherRuleForSameSkuCode = new UnitCountPricingRuleEntity("A", 40, 50);
        final UnitCountPricingRuleEntity anotherRuleForDifferentSkuCode = new UnitCountPricingRuleEntity("B", 40, 50);
        underTest.store(rule);
        underTest.store(anotherRuleForSameSkuCode);
        underTest.store(anotherRuleForDifferentSkuCode);

        final List<UnitCountPricingRuleEntity> actual = underTest.findRulesBySkuCode("A");

        assertThat(actual).usingRecursiveComparison().isEqualTo(Arrays.asList(rule, anotherRuleForSameSkuCode));
    }
}