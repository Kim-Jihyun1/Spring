package com.codestates.coffee;

import com.codestates.coffee.dto.CoffeePatchDto;
import com.codestates.coffee.dto.CoffeePostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
@Validated
public class CoffeeController {
//    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();
//
//    @PostConstruct
//    public void init() {
//        Map<String, Object> coffee1 = new HashMap<>();
//        long coffeeId = 1L;
//        coffee1.put("coffeeId", coffeeId);
//        coffee1.put("korName", "바닐라 라떼");
//        coffee1.put("engName", "Vanilla Latte");
//        coffee1.put("price", 4500);
//
//        coffees.put(coffeeId, coffee1);
//    }

    // 커피 정보 등록
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        return new ResponseEntity<>(coffeePostDto, HttpStatus.CREATED);
    }

    // 커피 정보 수정
    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        return new ResponseEntity<>(coffeePatchDto, HttpStatus.OK);
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
        // No need business logic

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
