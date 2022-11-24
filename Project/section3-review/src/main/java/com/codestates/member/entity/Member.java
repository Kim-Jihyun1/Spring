package com.codestates.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//@Setter // Member 클래스 내부적으로 getter, setter 메서드가 작성되어 있다
//@NoArgsConstructor // Member 클래스에 추가된 모든 멤버 변수를 피라미터로 갖는 Member 생성자를 자동으로 생성
@AllArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
public class Member {
    private long memberId;
    private String email;
    private String name;
    private String phone;
}
