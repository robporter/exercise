package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.SkuRepository;
import com.itv.checkout.persistence.entity.SkuEntity;

import java.util.List;
import java.util.Optional;

public class InMemorySkuRepository implements SkuRepository {

    private final List<SkuEntity> inventory;

    public InMemorySkuRepository(final List<SkuEntity> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void store(final SkuEntity skuEntity) {
        inventory.add(skuEntity);
    }

    @Override
    public Optional<SkuEntity> findSkuByCode(final String code) {
        return inventory.stream()
                .filter(skuEntity -> skuEntity.getCode().equals(code))
                .findAny();
    }
}
