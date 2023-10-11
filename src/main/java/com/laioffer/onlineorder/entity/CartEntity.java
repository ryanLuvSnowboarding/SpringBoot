package com.laioffer.onlineorder.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("carts") //tell Spring this entity is associated with carts table in DB
public record CartEntity(
        @Id Long id, //using @signId to specify id is the primary key
        Long customerId,
        Double totalPrice
) {
}

