package com.itv.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    private InventoryService underTest;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void beforeEach() {
        underTest = new InventoryService(inventoryRepository);
    }

    @Test
    void addSkuStoresSku() {

        final Sku sku = new Sku("A");

        underTest.addSku(sku);

        verify(inventoryRepository).store(sku);
    }

    @Test
    void addSkuDoesNotAddDuplicates() {

        final Sku sku = new Sku("A");
        given(inventoryRepository.findSkuByCode(sku.getCode())).willReturn(Optional.of(sku));

        final DuplicateSKUException actual = assertThrows(DuplicateSKUException.class, () -> underTest.addSku(sku));

        verify(inventoryRepository, never()).store(sku);
        assertThat(actual.getMessage()).isEqualTo("Duplicate SKU");
    }

}
