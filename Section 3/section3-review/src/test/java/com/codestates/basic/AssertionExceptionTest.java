package com.codestates.basic;

import com.codestates.CryptoCurrency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertionExceptionTest {
    @DisplayName("throws NullPointerException when map.get()")
    @Test
    public void assertionThrowExceptionTest() {
        // (NullPointerException 발생이 기대되는 예외 클래스, 테스트 대상 메서드)
        assertThrows(NullPointerException.class, () -> getCryptoCurrency("XRP"));
    }

    private String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit).toUpperCase();
        /*
         전달한 XRP 키에서 해당하는 암호 화폐가 있는지를 map에서 찾음
         XRP에 해당하는 암호 화폐는 존재하지 않으므로, map에서 반환된 값은 null
         map에서 반환된 값이 nulll인 상태에서 toUpperCase()를 호출했으므로 NullPointerException 발생

         NullPointerException이 발생할 것이라 기대했기 때문에 테스트 결과는 passed
         */
    }
}
