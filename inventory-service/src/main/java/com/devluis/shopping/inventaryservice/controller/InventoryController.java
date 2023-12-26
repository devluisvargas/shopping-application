package com.devluis.shopping.inventaryservice.controller;

import com.devluis.shopping.inventaryservice.models.dto.InventoryResponse;
import com.devluis.shopping.inventaryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCodes") List<String> skuCodes){
        log.info("start request /isInStock {}", skuCodes);
        return inventoryService.isInStock(skuCodes);
    }
}
