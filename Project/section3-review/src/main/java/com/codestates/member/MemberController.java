package com.codestates.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/members") // produces 설정 제거 (value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    // 회원 정보를 등록하는 핸들러 메서드
    @PostMapping
    public ResponseEntity postMember(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("phone") String phone) {
        System.out.println("# email: " + email);
        System.out.println("# name: " + name);
        System.out.println("# phone: " + phone);

//        String response =
//                "{\"" +
//                        "email\":\"" + email + "\"," +
//                        "\"name\":\"" + name + "\",\"" +
//                        "phone\":\"" + phone + "\"}";
//        return response;
        // JSON 문자열을 수작업으로 작성한 부분을 Map 객체로 대체
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        // 리턴 값을 ResponseEntity 객체로 변경
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    // 특정 회원 정보 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK); // 리턴 값을 ResponseEntity 객체로 변경
    }

    // 전체 회원 목록 조회
    @GetMapping
    public ResponseEntity getMembers() {
        System.out.println("# get Members");

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK); // 리턴 값을 ResponseEntity 객체로 변경
    }
}
