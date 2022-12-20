package com.codestates.helper;

import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;

import java.util.List;

public class StampCalculatorTestWithoutJUnit {
    public static void main(String[] args) {
        calculateStampCountTest(); // 첫 번째 단위 테스트
        calculateEarnedStampCountTest(); // 두 번째 단위 테스트
    }

    public static void calculateStampCountTest() {
        // given : 테스트에 필요한 전제 조건
        int nowCount = 5;
        int earned = 3;

        // when : 테스트 할 대상(동작)
        int actual = StampCalculator.calculateStampCount(nowCount, earned);
        int expected = 7; // 기대값

        // then : 테스트 결과 검증
        System.out.println(expected == actual); // 계산 결과 = 8 -> false
    }

    public static void calculateEarnedStampCountTest() {
        // given
        Order order = new Order();
        OrderCoffee orderCoffee1 = new OrderCoffee();
        orderCoffee1.setQuantity(3);

        OrderCoffee orderCoffee2 = new OrderCoffee();
        orderCoffee2.setQuantity(5);

        order.setOrderCoffees(List.of(orderCoffee1, orderCoffee2));

        // when
        int actual = StampCalculator.calculateEarnedStampCount(order);
        int expected = orderCoffee1.getQuantity() + orderCoffee2.getQuantity();

        // then
        System.out.println(expected == actual);
    }
}
