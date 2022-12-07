package com.codestates.hemcrest;

import com.codestates.CryptoCurrency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertionExceptionHamcrestTest {
    @DisplayName("throws NullPointerException when map.get()")
    @Test
    public void assertionThrowExceptionTest() {
        // 예외 테스트는 Hamcrest로만 Assertion을 구성하기 어려움
        // 그러므로 JUnit의 assertThrows() 메서드를 이용해 리턴 값을 전달 받음
        Throwable actualException = assertThrows(NullPointerException.class,
                () -> getCryptoCurrency("XRP"));   // 실행 결과: passed

        // throw된 Exception 타입이 기대했던 Exception 타입과 일치하는지 추가로 검증함
        assertThat(actualException.getClass(), is(NullPointerException.class));  // 실행 결과: passed (결과 값인 actualException.getClass()이 null이기 때문)
    }

    private String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit).toUpperCase();
    }
}
