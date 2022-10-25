package com.codestates.section3week22.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private long memberId;
    private long coffeeId;
}
