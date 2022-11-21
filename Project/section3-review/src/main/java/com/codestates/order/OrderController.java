package com.codestates.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController {
    // 회원이 주문한 커피 주문 정보 등록
    @PostMapping
    public ResponseEntity postOrder(@RequestParam("memberId") long memberId, @RequestParam("coffeeId") long coffeeId) {
        System.out.println("# memberId: " + memberId);
        System.out.println("# coffeeId: " + coffeeId);

        Map<String, Long> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("coffeeId", coffeeId);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // 특정 주문 정보 조회
    @GetMapping
    public ResponseEntity getOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# orderId: " + orderId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 주문 목록 조회
    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Orders");

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
