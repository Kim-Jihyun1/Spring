package com.codestates.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HelloJUnitTest {
    @DisplayName("Hello JUnit Test") // 테스트 케이스 실행 시, 실행 결과 창에 표시되는 이름 지정
    @Test
    public void assertionTest() {
        String expected = "Hello, JUnit";
        String actual = "Hello, World";

//        assertEquals(expected, actual);   // failed
        assertNotEquals(expected, actual);
    }
}
/*
실행 결과: failed

expected: <Hello, JUnit> but was: <Hello, World>
Expected :Hello, JUnit
Actual   :Hello, World
 */