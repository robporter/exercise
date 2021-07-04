package com.itv.checkout.persistence.repository;

import com.itv.checkout.persistence.InventoryRepository;
import com.itv.checkout.persistence.entity.SkuEntity;

import java.util.List;
import java.util.Optional;

public class InMemoryInventoryRepository implements InventoryRepository {

    private final List<SkuEntity> inventory;

    public InMemoryInventoryRepository(final List<SkuEntity> inventory) {
        this.inventory = inventory;
    }

    @Override
    public void store(final SkuEntity skuEntity) {
        inventory.add(skuEntity);
    }

    @Override
    public Optional<SkuEntity> findSkuByCode(final String code) {
        return inventory.stream().filter(skuEntity -> skuEntity.getCode().equals(code)).findAny();
    }
}
