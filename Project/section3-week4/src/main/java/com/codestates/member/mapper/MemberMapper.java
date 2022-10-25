package com.codestates.member.mapper;

import com.codestates.member.MemberPatchDto;
import com.codestates.member.MemberPostDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        return new Member(0L,
                memberPostDto.getEmail(),
                memberPostDto.getName(),
                memberPostDto.getPhone());
    }

    public Member memberPatchDtoToMember(MemberPatchDto memberPatchDto) {
        return new Member(memberPatchDto.getMemberId(),
                null,
                memberPatchDto.getName(),
                memberPatchDto.getPhone());
    }

    public MemberResponseDto memberToMemberResponseDto(Member member) {
        return new MemberResponseDto(member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone());
    }
}
