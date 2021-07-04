package com.itv.checkout.domain.service;

import com.itv.checkout.domain.converter.SkuConverter;
import com.itv.checkout.domain.converter.UnitCountPricingRuleConverter;
import com.itv.checkout.domain.exception.DuplicateSKUException;
import com.itv.checkout.domain.model.Pricing;
import com.itv.checkout.domain.model.Sku;
import com.itv.checkout.persistence.PricingRuleRepository;
import com.itv.checkout.persistence.SkuRepository;
import com.itv.checkout.persistence.entity.SkuEntity;
import com.itv.checkout.persistence.entity.UnitCountPricingRuleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    private InventoryService underTest;

    @Mock
    private SkuRepository skuRepository;

    @Mock
    private SkuConverter skuConverter;

    @Mock
    private UnitCountPricingRuleConverter pricingRuleConverter;

    @Mock
    private PricingRuleRepository pricingRuleRepository;

    @BeforeEach
    void beforeEach() {
        underTest = new InventoryService(skuRepository, skuConverter, pricingRuleConverter, pricingRuleRepository);
    }

    @Test
    void addSkuStoresSkuDetails() {

        final Sku sku = new Sku("A", 2);
        final SkuEntity skuEntity = mock(SkuEntity.class);
        final Pricing unitCountPricingRule = new Pricing(1, sku.getPriceInPence());
        final UnitCountPricingRuleEntity unitCountPricingRuleEntity = mock(UnitCountPricingRuleEntity.class);
        given(skuConverter.toEntity(sku)).willReturn(skuEntity);
        given(pricingRuleConverter.toEntity(eq(sku.getCode()), refEq(unitCountPricingRule))).willReturn(unitCountPricingRuleEntity);

        underTest.addSku(sku);

        verify(skuRepository).store(skuEntity);
        verify(pricingRuleRepository).store(unitCountPricingRuleEntity);
    }

    @Test
    void addSkuDoesNotAddDuplicates() {

        final Sku sku = new Sku("A", 2);
        given(skuRepository.findSkuByCode(sku.getCode())).willReturn(Optional.of(mock(SkuEntity.class)));

        final DuplicateSKUException actual = assertThrows(DuplicateSKUException.class, () -> underTest.addSku(sku));

        verify(skuRepository, never()).store(any());
        assertThat(actual.getMessage()).isEqualTo("Duplicate SKU");
    }

    @Test
    void addPricingRule() {
        final Sku sku = new Sku("A", 2);
        final Pricing pricing = new Pricing(20, 100);
        final UnitCountPricingRuleEntity expected = new UnitCountPricingRuleEntity(sku.getCode(), 100, 20);
        given(pricingRuleConverter.toEntity(sku.getCode(), pricing)).willReturn(expected);

        underTest.addPricingRule(sku.getCode(), pricing);

        verify(pricingRuleRepository).store(expected);
    }
}
