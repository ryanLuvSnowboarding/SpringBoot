package com.laioffer.onlineorder.model;

import com.laioffer.onlineorder.entity.CartEntity;

import java.util.List;
//the purpose of DTO is only for transferring data from Entity
public record CartDto(
        Long id,
        Double totalPrice,
        List<OrderItemDto> orderItems
) {
    public CartDto(CartEntity entity, List<OrderItemDto> orderItems) {
        this(
                entity.id(),
                entity.totalPrice(),
                orderItems
        );
    }
}
