package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;

    private Coffee.CoffeeStatus coffeeStatus;

    public String getCoffeeStatus() {
        return coffeeStatus.getStatus();
    }
}
