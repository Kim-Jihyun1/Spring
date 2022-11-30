package com.codestates.order.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class OrderCoffeeDto {
    @Positive
    private long CoffeeId;

    @Positive
    private int quantity;
}
