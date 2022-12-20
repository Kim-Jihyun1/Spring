package com.codestates.coffee;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vi/coffees")
public class CoffeeController {
    @PostMapping
    public String postCoffee(@RequestParam("korName") String korName,
                             @RequestParam("engName") String engName,
                             @RequestParam("price") String price) {
        System.out.println("# korName : " + korName);
        System.out.println("# engName : " + engName);
        System.out.println("# price : " + price);

        String response =
                "{\"" +
                        "korName\":\"" + korName + "\"," +
                        "\"engName\":\"" + engName + "\"," +
                        "price\":\"" + price +
                "\"}";
        return response;
    }
    @GetMapping("/{coffee-id}")
    public String getCoffee(@PathVariable("coffee-id")long coffeeId) {
        System.out.println("# coffeeId : " + coffeeId);

        // not implementation
        return null;
    }
    @GetMapping
    public String getCoffees() {
        System.out.println("# get Coffees");

        // not implementation
        return null;
    }
}
