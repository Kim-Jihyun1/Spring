package com.codestates.coffee.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CoffeePostDto {
    @NotBlank
    private String korName;

    @NotBlank
    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$", // 대소문자 모두 가능, 워드 사이에 한 칸의 공백만 허용
            message = "커피 이름은 영문이어야 합니다.")
    private String engName;

    @Range(min = 100, max = 50000)
    private int price;

    public String getKorName() {
        return korName;
    }

    public String getEngName() {
        return engName;
    }

    public int getPrice() {
        return price;
    }
}
