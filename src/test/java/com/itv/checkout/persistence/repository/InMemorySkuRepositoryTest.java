package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.entity.SkuEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class InMemorySkuRepositoryTest {

    private InMemorySkuRepository underTest;
    private List<SkuEntity> inventory;

    @BeforeEach
    void beforeEach() {
        inventory = new ArrayList<>();
        underTest = new InMemorySkuRepository(inventory);
    }

    @Test
    void stores() {
        final SkuEntity skuEntity = mock(SkuEntity.class);

        underTest.store(skuEntity);

        assertThat(inventory).contains(skuEntity);
    }

    @Test
    void findsBySkyCode() {

        final SkuEntity skuEntityToFind = new SkuEntity("A");
        final SkuEntity anotherSkuEntity = new SkuEntity("B");
        inventory.add(anotherSkuEntity);
        inventory.add(skuEntityToFind);

        final Optional<SkuEntity> actual = underTest.findSkuByCode(skuEntityToFind.getCode());

        assertThat(actual).usingRecursiveComparison().isEqualTo(Optional.of(skuEntityToFind));
    }
}