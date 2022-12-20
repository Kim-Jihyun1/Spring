//package com.codestates.JUnit_homework;
//
//import com.codestates.helper.RandomPasswordGenerator;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class RandomPasswordGeneratorTest {
//    @DisplayName("랜덤 패스워드 생성 단위 테스트")
//    @Test
//    public void generateTest() {
//        // given
//        int numberOfUpper = 2;
//        int numberOfLower = 5;
//        int numberOfNum = 2;
//        int numberOfSpecial = 4;
//        int length = numberOfUpper + numberOfLower + numberOfNum + numberOfSpecial;
//
//        // when
//        String randomPassword = RandomPasswordGenerator.generate(numberOfUpper, numberOfLower, numberOfNum, numberOfSpecial);
//
//        // then
//        assertEquals(length, randomPassword.length());
//        assertEquals(numberOfUpper, randomPassword.chars().filter(Character::isUpperCase).count());
//
//        assertEquals(numberOfLower, randomPassword.chars().filter(Character::isLowerCase).count());
//
//        assertEquals(numberOfNum, randomPassword.chars().filter(Character::isDigit).count());
//
//        assertEquals(numberOfSpecial,
//                randomPassword
//                        .chars()
//                        .filter(ch -> !(Character.isUpperCase(ch) ||
//                                Character.isLowerCase(ch) ||
//                                Character.isDigit(ch)))
//                        .count());
//    }
//}
