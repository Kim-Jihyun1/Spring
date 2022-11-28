package com.codestates.jpa.sequence;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // SEQUENCE 전략 (DB SEQUENCE 이용)
    private Long memberId;

    public Member(Long memberId) {
        this.memberId = memberId;
    }
}
