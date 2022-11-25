package com.codestates.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private long memberId;
    private long coffeeId;
}
