package com.codestates.section3week22.coffee.mapper;

import com.codestates.section3week22.coffee.dto.CoffeePatchDto;
import com.codestates.section3week22.coffee.dto.CoffeePostDto;
import com.codestates.section3week22.coffee.dto.CoffeeResponseDto;
import com.codestates.section3week22.coffee.entity.Coffee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
