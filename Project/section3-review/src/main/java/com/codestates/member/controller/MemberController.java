package com.codestates.member.controller;

import com.codestates.response.MultiResponseDto;
import com.codestates.response.SingleResponseDto;
import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v8/members")
@Validated // 유효성 검증
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    // MemberMapper DI
    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        Member member = memberService.createMember(mapper.memberPostDtoToMember(memberDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)),
                HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        Member member = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member)),
                HttpStatus.OK);
    }

    // 특정 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponseDto(member))
                , HttpStatus.OK);
    }

    // 전체 회원 목록 조회
    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponseDtos(members),
                        pageMembers),
                HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
