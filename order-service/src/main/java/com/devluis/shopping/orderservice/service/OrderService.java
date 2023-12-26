package com.devluis.shopping.orderservice.service;

import com.devluis.shopping.orderservice.models.dto.OrderListItemsDto;
import com.devluis.shopping.orderservice.models.dto.OrderRequest;
import com.devluis.shopping.orderservice.models.entity.Order;
import com.devluis.shopping.orderservice.models.entity.OrderLineItems;
import com.devluis.shopping.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> list = orderRequest.getOrderListItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(list);

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
