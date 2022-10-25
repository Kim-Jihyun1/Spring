package com.codestates.section3week22.order.mapper;

import com.codestates.section3week22.order.Order;
import com.codestates.section3week22.order.OrderPostDto;
import com.codestates.section3week22.order.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    OrderResponseDto orderToOrderResponseDto(Order order);
}
