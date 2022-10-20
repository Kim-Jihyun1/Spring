package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members") // produces 설정 제거
public class MemberController {
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email,
                             @RequestParam("name") String name,
                             @RequestParam("phone") String phone) {
        // JSON 문자열을 수작업으로 작성한 부분을 Map 객체로 대체
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        // 리턴 값을 ResponseEntity  객체로 변경
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id")long memberId) {
        System.out.println("# memberId : " + memberId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
