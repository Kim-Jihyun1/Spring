package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v4/members")
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;
    // MemberMapper DI
    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberDto) {
        // MemberPostDto -> Member 변환
        Member member = mapper.memberPostDtoToMember(memberDto);
        Member response = memberService.createMember(member);

        // Member -> MemberResponseDto 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);

        // MemberPatchDto -> Member 변환
        Member response = memberService.updateMember(mapper.memberPatchDtoToMember(memberPatchDto));

        // Member -> MemberResponseDto 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member response = memberService.findMember(memberId);

        // Member -> MemberResponseDto 변환
        return new ResponseEntity<>(mapper.memberToMemberResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findMembers();

        // List<Member> -> MemberResponseDto 변환
        List<MemberResponseDto> response =
                members.stream()
                        .map(member -> mapper.memberToMemberResponseDto(member))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        System.out.println("# delete member");
        memberService.deleteMember(memberId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
