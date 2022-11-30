package com.codestates.member.dto;

import com.codestates.member.entity.Member;
import com.codestates.stamp.Stamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String phone;
    private Stamp stamp;
    private Member.MemberStatus memberStatus;

    public String getMemberStatus() {
        return memberStatus.getStatus();
    }

    public int getStamp() {
        return stamp.getStampCount();
    }
}
