//package com.codestates.tdd;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//public class PasswordValidatorTest {
//    @DisplayName("패스워드 유효성 검증 테스트: 모든 조건에 만족")
//    @Test
//    public void validatePassword() {
//        // given
//        String password = "Abcd1234!";
//
//        // when
//        PasswordValidator validator = new PasswordValidator();
//        Executable executable = () -> validator.validate(password);
//
//        // then
//        assertDoesNotThrow(executable);
//    }
//
//    @DisplayName("특수 문자가 없는 패스워드 테스트")
//    @Test
//    public void validatePasswordWithoutSpecialCharacter() {
//        // given
//        String password1 = "Abcd1234&!";
////        String password2 = "Abcd1234";
//
//        // when
//        PasswordValidator validator = new PasswordValidator();
//
//        Executable executable1 = () -> validator.validate(password1);
////        Executable executable2 = () -> validator.validate(password2);
//
//        // then
//        assertDoesNotThrow(executable1);
////        assertDoesNotThrow(executable2); ;
//    }
//}
