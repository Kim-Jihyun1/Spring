//package com.codestates.tdd;
//
//import java.util.regex.Pattern;
//
//public class PasswordValidator {
//
//    public void validate(String password) {
//        // PasswordValidator의 validate()에 무조건 Exception을 던지도록 수정 -> 조건을 만족해도 failed
////        throw new RuntimeException("Invalid password");
//
//        // 특수 문자를 포함하지 않는 경우에만 Exception을 던지도록 수정
//            // 1. 특수 문자를 포함하고 있는지 체크
////        boolean containSpecialCharacter =
////                password.chars()
////                        .anyMatch(ch -> !(Character.isDigit(ch) || Character.isAlphabetic(ch)));
//
//            // 2. 특수 문자를 포함하고 있지 않는 경우에만 Exception을 던짐
////        if (!containSpecialCharacter) {
////            throw new RuntimeException("Invalid password");
////        }
//
//        // 정규 표현식을 사용한 패스워드 유효성 검사
//        if (!Pattern.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,20}", password)) {
//            throw new RuntimeException("Invalid password");
//        }
//    }
//}
