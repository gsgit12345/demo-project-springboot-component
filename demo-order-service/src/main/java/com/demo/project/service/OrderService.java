package com.demo.project.service;

import com.demo.project.dto.OrderLineItemsDto;
import com.demo.project.dto.OrderRequest;
import com.demo.project.model.Order;
import com.demo.project.model.OrderLineItems;
import com.demo.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest)
    {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        //orderRequest.getOrderLineItemsDtoList().stream()
          //      .map(orderLineItemsDto -> mapToDto(orderLineItemsDto));
       List<OrderLineItems> orderLineItemsList=orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).collect(Collectors.toList());
       order.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
