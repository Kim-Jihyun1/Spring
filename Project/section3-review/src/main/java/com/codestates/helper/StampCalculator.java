package com.codestates.helper;

import com.codestates.order.entity.Order;

public class StampCalculator {
    public static int calculateStampCount(int nowCount, int earned) {
        return nowCount + earned;
    }

    // 주문 정보에서 얻게 되는 스탬프 개수를 계산
    public static int calculateEarnedStampCount(Order order) {
        return order.getOrderCoffees().stream()
                .map(orderCoffee -> orderCoffee.getQuantity())
                .mapToInt(quantity -> quantity)
                .sum();
    }
}
