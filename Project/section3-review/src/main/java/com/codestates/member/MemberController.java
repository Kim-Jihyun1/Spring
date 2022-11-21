package com.codestates.member;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/members", produces = {MediaType.APPLICATION_JSON_VALUE}) // JSON 형식의 데이터를 응답 데이터로 전송
public class MemberController {
    // 회원 정보를 등록하는 핸들러 메서드
    @PostMapping
    public String postMember(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("phone") String phone) {
        System.out.println("# email: " + email);
        System.out.println("# name: " + name);
        System.out.println("# phone: " + phone);

        String response =
                "{\"" +
                        "email\":\"" + email + "\"," +
                        "\"name\":\"" + name + "\",\"" +
                        "phone\":\"" + phone + "\"}";
        return response;
    }

    // 특정 회원 정보 조회
    @GetMapping("/{member-id}")
    public String getMember(@PathVariable("member-id") long memberId) {
        System.out.println("# memberId: " + memberId);

        // not implementation
        return null;
    }

    // 전체 회원 목록 조회
    @GetMapping
    public String getMembers() {
        System.out.println("# get Members");

        // not implementation
        return null;
    }
}
