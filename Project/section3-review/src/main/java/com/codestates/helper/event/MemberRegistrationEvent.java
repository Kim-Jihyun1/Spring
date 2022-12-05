package com.codestates.helper.event;

import com.codestates.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberRegistrationEvent {
    private Member member;

    public MemberRegistrationEvent(Member member) {
        this.member = member;
    }
}
