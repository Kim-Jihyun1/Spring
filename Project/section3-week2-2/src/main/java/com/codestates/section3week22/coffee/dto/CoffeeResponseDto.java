package com.codestates.section3week22.coffee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
}
