package com.codestates.member.mapper;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);

    @Mapping(source = "member.stamp.stampCount", target = "stampCount")
    @Mapping(source = "member.memberStatus.status", target = "memberStatus")
    MemberDto.Response memberToMemberResponseDto(Member member);

    List<MemberResponseDto> membersToMemberResponseDtos(List<Member> members);
}
