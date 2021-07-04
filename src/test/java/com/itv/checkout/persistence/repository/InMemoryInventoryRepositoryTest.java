package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.entity.SkuEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InMemoryInventoryRepositoryTest {

    private InMemoryInventoryRepository underTest;
    private List<SkuEntity> inventory;

    @BeforeEach
    void beforeEach() {
        inventory = new ArrayList<>();
        underTest = new InMemoryInventoryRepository(inventory);
    }

    @Test
    void storeUpdatesInventory() {
        final SkuEntity skuEntity = mock(SkuEntity.class);

        underTest.store(skuEntity);

        assertThat(inventory).contains(skuEntity);
    }

    @Test
    void findBySkuCodeFindsInInventory() {

        final SkuEntity skuEntityToFind = new SkuEntity("A", Collections.emptyList());
        final SkuEntity anotherSkuEntity = new SkuEntity("B", Collections.emptyList());
        inventory.add(anotherSkuEntity);
        inventory.add(skuEntityToFind);

        final Optional<SkuEntity> actual = underTest.findSkuByCode("A");

        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(skuEntityToFind));
    }
}