package com.codestates.example.stub_example.fix_stub_controller;

import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.stamp.Stamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


/**
 * - DI 적용
 * - Mapstruct Mapper 적용
 * - @ExceptionHandler 적용
 */
@RestController
@RequestMapping("/v12/members")
@Validated
@Slf4j
public class MemberControllerV12 {

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        MemberDto.response response =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "Hong Gil Dong",
                        "010-1111-1111",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch requestBody) {
        MemberDto.response response =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "Hong Gil Dong",
                        "010-2222-2222",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());
        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        MemberDto.response response =
                new MemberDto.response(1L,
                        "hgd@gmail.com",
                        "Hong Gil Dong",
                        "010-1111-1111",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());
        return new ResponseEntity<>(
                new SingleResponseDto<>(response),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        MemberDto.response response1 =
                new MemberDto.response(1L,
                        "hgd1@gmail.com",
                        "Hong Gil Dong1",
                        "010-1111-1111",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());

        MemberDto.response response2 =
                new MemberDto.response(2L,
                        "hgd2@gmail.com",
                        "Hong Gil Dong2",
                        "010-2222-2222",
                        Member.MemberStatus.MEMBER_ACTIVE,
                        new Stamp());
        Page<MemberDto.response> pageMembers =
                new PageImpl<>(List.of(response1, response2),
                        PageRequest.of(0, 10, Sort.by("memberId").descending()), 2);

        return new ResponseEntity<>(
                new MultiResponseDto<>(List.of(response1, response2), pageMembers), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
