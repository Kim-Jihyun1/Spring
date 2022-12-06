package com.codestates.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BeforeEach1Test {
    @BeforeEach
    public void init() {
        System.out.println("Pre-processing before each test case");
    }

    /*
    테스트 실행 결과 : Pre-processing before each test case -> 콘솔에 두 번 출력됨
    @BeforeEach 를 추가한 메서드는 테스트 케이스가 각각 실행될 때마다 테스트 케이스 실행 직전에 먼저 실행되어 초기화 작업 등을 진행할 수 있음
     */
    @DisplayName("@BeforeEach Test1")
    @Test
    public void beforeEachTest() {
    }

    @DisplayName("@BeforeEach Test2")
    @Test
    public void beforeEachTest2() {
    }
}
