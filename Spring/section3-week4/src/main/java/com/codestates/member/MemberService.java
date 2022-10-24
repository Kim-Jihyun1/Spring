package com.codestates.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member createMember(Member member) {
        // member 객체는 나중에 DB에 저장 이후 되돌려 받는 것으로 변경
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member) {
        // member 객체는 DB에 업데이트 이후 되돌려 받는 것으로 변경
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId) {
        // member 객체는 나중에 DB에서 조회
        Member member =
                new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        return member;
    }

    public List<Member> findMembers() {
        // member 객체는 나중에 DB에서 조회
        List<Member> members = List.of(
                new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2, "lml@gmail.com", "이몽룡", "010-1111-2222")
        );
        return members;
    }

    public void deleteMember(long memberId) {
    }
}
