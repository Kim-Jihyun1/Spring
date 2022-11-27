package com.codestates.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter // Member 클래스 내부적으로 getter, setter 메서드가 작성되어 있다
@NoArgsConstructor // Member 클래스에 추가된 모든 멤버 변수를 피라미터로 갖는 Member 생성자를 자동으로 생성
//@AllArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성
@Entity
public class Member {
    @Id // @Entity 와 @Id를 추가하면 JPA에서 해당 클래스를 엔티티 클래스로 인식
    @GeneratedValue // 식별자 자동 설정
    private long memberId;

    private String email;

    private String name;

    private String phone;

    public Member(String email) {
        this.email = email;
    }
}
