package com.codestates.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloJUnitTest {
    @DisplayName("Hello JUnit Test") // 테스트 케이스 실행 시, 실행 결과 창에 표시되는 이름 지정
    @Test
    public void assertionTest() {
        String expected = "Hello, JUnit";
        String actual = "Hello, JUnit";

        assertEquals(expected, actual);
    }
}
