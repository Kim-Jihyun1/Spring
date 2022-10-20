package com.codestates.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/orders") // produces 설정 제거
public class OrderController {
    @PostMapping
    public ResponseEntity postOrder(@RequestParam("memberId")long memberId,
                                    @RequestParam("coffeeId")long coffeeId) {
        // JSON 문자열을 수작업으로 작성한 부분을 Map 객체로 대체
        Map<Object, Object> map = new HashMap<>(); // String이 아닌 다른 타입의 데이터를 map에 추가하기 위해서는 Object로 지정
        map.put("memberId", memberId);
        map.put("coffeeId", coffeeId);

        // 리턴 값을 ResponseEntity  객체로 변경
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id")long orderId) {
        System.out.println("# orderId : " + orderId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Orders");

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
