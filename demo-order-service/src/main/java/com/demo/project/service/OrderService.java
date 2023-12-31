package com.demo.project.service;

import com.demo.project.dto.InventoryResponse;
import com.demo.project.dto.OrderLineItemsDto;
import com.demo.project.dto.OrderRequest;
import com.demo.project.event.OrderPlacedEvent;
import com.demo.project.model.Order;
import com.demo.project.model.OrderLineItems;
import com.demo.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        //orderRequest.getOrderLineItemsDtoList().stream()
        //      .map(orderLineItemsDto -> mapToDto(orderLineItemsDto));
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);
        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).collect(Collectors.toList());
        //call the  inventory service and place order if product is in stock
        //http://localhost:8082/api/inventory
        //http://inventory-service/api/inventory  using eureka server

        //creating our span id and custome traceid
        Span invent = tracer.nextSpan().name("InventoryServiceLookup");
        try (Tracer.SpanInScope spanInScope = tracer.withSpan(invent.start())) {
            InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get().uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve().bodyToMono(InventoryResponse[].class).block(); //block() is used to make synchronous request

            boolean result = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);
            if (result) {
                orderRepository.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                return "order place succesfully";

            } else {
                throw new IllegalArgumentException("Product is not in stock");
            }
        } finally {

        }
/*        InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get().uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block(); //block() is used to make synchronous request

        boolean result = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);
        if (result) {
            orderRepository.save(order);
            return "order place succesfully";

        } else {
            throw new IllegalArgumentException("Product is not in stock");
        }*/
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
