package com.codestates.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> coffee1 = new HashMap<>();
        long coffeeId = 1L;
        coffee1.put("coffeeId", coffeeId);
        coffee1.put("korName", "바닐라 라떼");
        coffee1.put("engName", "Vanilla Latte");
        coffee1.put("price", 4500);

        coffees.put(coffeeId, coffee1);
    }

    // 커피 정보 등록
    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("korName") String korName, @RequestParam("engName") String engName, @RequestParam("price") String price) {
        System.out.println("# korName: " + korName);
        System.out.println("# engName: " + engName);
        System.out.println("# price: " + price);

        Map<String, String> map = new HashMap<>();
        map.put("korName", korName);
        map.put("engName", engName);
        map.put("price", price);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // 커피 정보 수정
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @RequestParam("korName") long korName, @RequestParam("engName") long engName) {
        // No need Business logic

        Map<String, Object> coffee = coffees.get(coffeeId);

        if (coffee == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            coffee.put("korName", korName);
            coffee.put("engName", engName);

        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    // 특정 커피 정보 조회
    @GetMapping
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 커피 목록 조회
    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("# get Coffees");

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 커피 정보 삭제
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        if (coffees.containsKey(coffeeId))
            coffees.remove(coffeeId);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
