package com.codestates.member;

import com.codestates.member.dto.MemberPatchDto;
import com.codestates.member.dto.MemberPostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/v1/members")
@Validated // 유효성 검증이 정상적으로 수행되기 위함
public class MemberController {
//    private final Map<Long, Map<String, Object>> members = new HashMap<>();
//
//    @PostConstruct
//    public void init() {
//        Map<String, Object> member1 = new HashMap<>();
//        long memberId = 1L;
//        member1.put("memberId", memberId);
//        member1.put("email", "hgd@gmail.com");
//        member1.put("name", "홍길동");
//        member1.put("phone", "010-1234-5678");
//
//        members.put(memberId, member1);
//    }

    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        return new ResponseEntity<>(memberPostDto, HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") @Min(1) long memberId, // @Min(1) : memberId가 1 이상의 숫자
                                      @Valid @RequestBody MemberPatchDto memberPatchDto) {
        memberPatchDto.setMemberId(memberId);
        memberPatchDto.setName("홍길동");

        // No need Business logic

        return new ResponseEntity<>(memberPatchDto, HttpStatus.OK);
    }

    // 특정 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 전체 회원 목록 조회
    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원 정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        // No need Business logic

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 회원 정보가 삭제되어 존재하지 않으므로 204 No Content를 지정
    }
}
