//package com.codestates.JUnit_homework;
//
//import com.codestates.helper.StampCalculator;
//import com.codestates.order.entity.Order;
//import com.codestates.order.entity.OrderCoffee;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class StampCalculatorTest {
//    @Test
//    @DisplayName("스탬프 카운트 계산 단위 테스트")
//    public void calculateStampCountTest() {
//        // given
//        int nowCount = 5;
//        int earned = 3;
//
//        // when
//        int actual = StampCalculator.calculateStampCount(5, 3);
//        int expected = nowCount + earned;
//
//        // then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("주문 후 누적 스탬프 카운트 계산 단위 테스트")
//    public void calculateEarnedStampCountTest(){
//        // given
//        Order order = new Order();
//        OrderCoffee orderCoffee1 = new OrderCoffee();
//        orderCoffee1.setQuantity(3);
//
//        OrderCoffee orderCoffee2 = new OrderCoffee();
//        orderCoffee2.setQuantity(5);
//
//        order.setOrderCoffees(List.of(orderCoffee1, orderCoffee2));
//
//        // when
//        int actual = StampCalculator.calculateEarnedStampCount(order);
//        int expected = orderCoffee1.getQuantity() + orderCoffee2.getQuantity();
//
//        // then
//        assertEquals(expected, actual);
//    }
//}
