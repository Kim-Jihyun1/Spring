package com.codestates.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BeforeEach2Test {
    private Map<String, String> map;

    @BeforeEach
    public void init() {
        map = new HashMap<>();
        map.put("BTC", "Bitcoin");
        map.put("ETH", "Ethereum");
        map.put("ADA", "ADA");
        map.put("POT", "Polkadot");
    }

    @DisplayName("Test case 1")
    @Test
    public void beforeEachTest() {
        map.put("XRP", "Ripple"); // assertDoesNotThrow()로 Assertion 하기 전에 map에 XRP 값을 추가함

        // assertDoesNotThrow() 는 예외가 발생하지 않는다고 기대하는 Assertion 메서드
        assertDoesNotThrow(() -> getCryptoCurrency("XRP")); // 실행 결과 : passed
    }

    @DisplayName("Test case 2")
    @Test
    public void beforeEachTest2() {
        /**
         * Assertion 하기 전에 map에 XRP를 추가하지 않았음
         * Test case 2가 실행되기 전에 init() 메서드가 호출되면서 사용했던 map 객체가 초기화됨
         * 실행 결과 : failed
         */
        System.out.println(map);
//        assertDoesNotThrow(() -> getCryptoCurrency("XRP"));

        assertThrows(NullPointerException.class, () -> getCryptoCurrency("XRP"));
    }

    private String getCryptoCurrency(String unit) {
        return map.get(unit).toUpperCase();
    }
}
