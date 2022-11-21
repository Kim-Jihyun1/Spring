package com.codestates.order;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController {
    // 회원이 주문한 커피 주문 정보 등록
    @PostMapping
    public String postOrder(@RequestParam("memberId") long memberId, @RequestParam("coffeeId") long coffeeId) {
        System.out.println("# memberId: " + memberId);
        System.out.println("# coffeeId: " + coffeeId);

        String response =
                "{\"" +
                        "memberId\":\"" + memberId + "\"," +
                        "\"coffeeId\":\"" + coffeeId + "\"}";
        return response;
    }

    // 특정 주문 정보 조회
    @GetMapping
    public String getOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# orderId: " + orderId);

        // not implementation
        return null;
    }

    // 전체 주문 목록 조회
    @GetMapping
    public String getOrders() {
        System.out.println("# get Orders");

        // not implementation
        return null;
    }
}
