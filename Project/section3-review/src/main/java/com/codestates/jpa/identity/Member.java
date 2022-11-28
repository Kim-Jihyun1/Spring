package com.codestates.jpa.identity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
//@Table(name = "USERS") // 테이블명을 설정 (기본 값은 클래스명) -> 옵션 (주로 테이블명과 클래스명이 달라야 할 때 사용)
@Entity // @Id와 함께 사용 -> 필수
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 기본키 생성 전략 (DB에서 기본키 대신 생성)
    private Long memberId;

    public Member(Long memberId) {
        this.memberId = memberId;
    }
}
