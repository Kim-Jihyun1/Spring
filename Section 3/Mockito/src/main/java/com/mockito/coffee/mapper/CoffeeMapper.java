package com.mockito.coffee.mapper;

import com.mockito.coffee.dto.CoffeePatchDto;
import com.mockito.coffee.dto.CoffeePostDto;
import com.mockito.coffee.dto.CoffeeResponseDto;
import com.mockito.coffee.entity.Coffee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
