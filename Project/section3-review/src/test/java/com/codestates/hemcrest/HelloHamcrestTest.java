package com.codestates.hemcrest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HelloHamcrestTest {
    @DisplayName("Hello Junit Test using hamcrest")
    @Test
    public void assertionTest1() {
        String expected = "Hello, World";
        String actual = "Hello, JUnit";

        // JUnit Assertion : assertEquals(expected, actual); -> 파라미터로 입력된 값의 변수명으로 대략적으로 어떤 검증을 할지 유추 할 수 있음
        // Hamcrest Matcher :
        assertThat(actual, is(equalTo(expected))); // 'assert that actual is equal to expected' 라는 문장으로 자연스럽게 읽힘
    }
}
/*
실행 결과: failed

Expected: is "Hello, World"
     but: was "Hello, JUnit"

Hemcrest의 Matcher를 사용하면 자연스러운 Assertion 문장을 구성할 수 있음
실행 결과가 failed인 경우에도 자연스러운 failed 메시지를 확인할 수 있어 가독성이 높음
 */
