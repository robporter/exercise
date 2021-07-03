package com.itv.checkout;

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addSku(final Sku sku) {
        inventoryRepository.store(sku);
    }
}
