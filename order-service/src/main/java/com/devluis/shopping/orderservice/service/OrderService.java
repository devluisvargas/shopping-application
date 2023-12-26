package com.devluis.shopping.orderservice.service;

import com.devluis.shopping.orderservice.models.dto.InventoryResponse;
import com.devluis.shopping.orderservice.models.dto.OrderListItemsDto;
import com.devluis.shopping.orderservice.models.dto.OrderRequest;
import com.devluis.shopping.orderservice.models.entity.Order;
import com.devluis.shopping.orderservice.models.entity.OrderLineItems;
import com.devluis.shopping.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    @Value("${ms-inventory.uri}")
    private String uriMsInventory;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> list = orderRequest.getOrderListItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(list);

        List<String> listSkuCode = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        /*
         * Call Inventory Service, and place order if product is in stock
         */
        InventoryResponse[] inventoryResponseArray = webClientBuilder
                .build()
                .get()
                .uri(uriMsInventory, uriBuilder -> uriBuilder.queryParam("skuCodes", listSkuCode).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);


        if(!allProductsInStock){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product is not in stock, please try again later");
        }
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderListItemsDto orderListItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderListItemsDto.getId());
        orderLineItems.setQuantity(orderListItemsDto.getQuantity());
        orderLineItems.setPrice(orderListItemsDto.getPrice());
        orderLineItems.setSkuCode(orderListItemsDto.getSkuCode());
        return orderLineItems;
    }
}
