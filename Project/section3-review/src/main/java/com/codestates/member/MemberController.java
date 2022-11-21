package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members")
public class MemberController {
    private final Map<Long, Map<String, Object>> members = new HashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Object> member1 = new HashMap<>();
        long memberId = 1L;
        member1.put("memberId", memberId);
        member1.put("email", "hgd@gmail.com");
        member1.put("name", "홍길동");
        member1.put("phone", "010-1234-5678");

        members.put(memberId, member1);
    }

    // 회원 정보 등록
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("phone") String phone) {
        System.out.println("# email: " + email);
        System.out.println("# name: " + name);
        System.out.println("# phone: " + phone);

        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);


        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // 회원 정보 수정
    @PatchMapping
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId, @RequestParam String phone) { // phone 정보 수정
        // No need Business logic

        Map<String, Object> member = members.get(memberId);

        if (member == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            member.put("phone", phone);

        return new ResponseEntity<>(member, HttpStatus.OK);
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
        if (members.containsKey(memberId))
            members.remove(memberId);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 회원 정보가 삭제되어 존재하지 않으므로 204 No Content를 지정
    }
}
