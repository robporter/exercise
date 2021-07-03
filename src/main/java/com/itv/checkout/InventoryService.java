package com.itv.checkout;

public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addSku(final Sku sku) {
        if (!inventoryRepository.findSkuByCode(sku.getCode()).isPresent()) {
            inventoryRepository.store(sku);
        }
    }
}
