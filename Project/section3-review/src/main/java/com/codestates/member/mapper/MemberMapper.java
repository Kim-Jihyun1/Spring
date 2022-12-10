package com.codestates.member.mapper;

import com.codestates.member.dto.MemberDto;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import javax.validation.Valid;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostDtoToMember(MemberDto.Post requestBody);
    Member memberPatchDtoToMember(MemberDto.Patch requestBody);

//    @Mapping(source = "member.stamp.stampCount", target = "stampCount")
//    @Mapping(source = "member.memberStatus.status", target = "memberStatus")
    MemberDto.response memberToMemberResponseDto(Member member);

    List<MemberDto.response> membersToMemberResponseDtos(List<Member> members);
}
