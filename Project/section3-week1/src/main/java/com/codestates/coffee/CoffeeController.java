package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vi/coffees")
public class CoffeeController {
    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("korName") String korName,
                                     @RequestParam("engName") String engName,
                                     @RequestParam("price") String price) {
        // JSON 문자열을 수작업으로 작성한 부분을 Map 객체로 대체
        Map<String, String> map = new HashMap<>();
        map.put("korName", korName);
        map.put("engName", engName);
        map.put("price", price);

        // 리턴 값을 ResponseEntity  객체로 변경
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id")long coffeeId) {
        System.out.println("# coffeeId : " + coffeeId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation
         return new ResponseEntity<>(HttpStatus.OK);
    }
}
