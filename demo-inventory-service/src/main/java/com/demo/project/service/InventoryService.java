package com.demo.project.service;

import com.demo.project.dto.InventoryResponse;
import com.demo.project.model.Inventory;
import com.demo.project.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        // return inventoryRepository.findBySkuCode(skuCode).isPresent();
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().
                map(inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode()).
                        isInStock(inventory.getQuantity() > 0).build()

                ).collect(Collectors.toList());

    }
}