package com.codestates.hemcrest;

import com.codestates.CryptoCurrency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertionNullHamcrestTest {
    @DisplayName("AssertionNull() Test")
    @Test
    public void assertNotNullTest() {
        String currencyName = getCryptoCurrency("ETH");

        // (JUnit) ETH 암호 화폐가 map에 존재하는지 테스트
//        assertNotNull(currencyName, "should be not null");

        // Hamcrest로 Not Null 테스트
        assertThat(currencyName, is(notNullValue()));
//        assertThat(currencyName, is(nullValue())); // 주석을 해제하면 실행 결과는 failed
    }

    private String getCryptoCurrency(String unit) {
        return CryptoCurrency.map.get(unit);
    }
}
