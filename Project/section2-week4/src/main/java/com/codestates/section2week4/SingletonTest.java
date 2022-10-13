package com.codestates.section2week4;

import com.codestates.section2week4.member.MemberService;

public class SingletonTest {
    static DependencyConfig dependencyConfig = new DependencyConfig();

    static MemberService memberService1 = dependencyConfig.memberService();
    static MemberService memberService2 = dependencyConfig.memberService();

    public static void main(String[] args) {
        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);
    }
}
