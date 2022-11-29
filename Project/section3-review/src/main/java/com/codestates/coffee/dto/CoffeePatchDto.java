package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import com.codestates.validator.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.util.Optional;

public class CoffeePatchDto {
    private long coffeeId;

    @NotSpace(message = "커피 이름은 공백이 아니어야 합니다.")
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", // 대소문자 모두 가능, 워드 사이에 한 칸의 공백만 허용
            message = "커피 이름은 영문이어야 합니다.")
    private String engName;
    private Optional<@Range(min = 100, max = 50000) Integer> price = Optional.empty();

    private Coffee.CoffeeStatus coffeeStatus;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public int getPrice() {
        return price.orElse(0);
    }
}
