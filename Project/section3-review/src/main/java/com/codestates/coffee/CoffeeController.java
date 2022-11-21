package com.codestates.coffee;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    // 커피 정보 등록
    @PostMapping
    public String postCoffee(@RequestParam("korName") String korName, @RequestParam("engName") String engName, @RequestParam("price") String price) {
        System.out.println("# korName: " + korName);
        System.out.println("# engName: " + engName);
        System.out.println("# price: " + price);

        String response =
                "{\"" +
                        "korName\":\"" + korName + "\"," +
                        "\"engName\":\"" + engName + "\",\"" +
                        "price\":\"" + price + "\"}";
        return response;
    }

    // 특정 커피 정보 조회
    @GetMapping
    public String getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("# coffeeId: " + coffeeId);

        // not implementation
        return null;
    }

    // 전체 커피 목록 조회
    @GetMapping
    public String getCoffees() {
        System.out.println("# get Coffees");

        // not implementation
        return null;
    }
}
