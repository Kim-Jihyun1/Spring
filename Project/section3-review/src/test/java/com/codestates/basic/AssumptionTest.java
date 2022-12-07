package com.codestates.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AssumptionTest {
    @DisplayName("Assumption Test")
    @Test
    public void assumptionTest() {
        // assumeTrue()는 특정 조건에서 선택적인 테스트가 필요한 경우 사용
        // 파라미터로 입력된 값이 true이면 아래 로직을 실행
        assumeTrue(System.getProperty("os.name").startsWith("Windows")); // 테스트 케이스를 실행하는 운영체제가 윈도우면 true

        System.out.println("execute?");
        assertTrue(processOnlyWindowsTask());
    }

    private boolean processOnlyWindowsTask() {
        return true;
    }
}
