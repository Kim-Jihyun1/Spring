package com.codestates.slice.mock;

import com.codestates.exception.BusinessLogicException;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class) // JUnit에서 Mockito의 기능을 사용하기 위함
public class MemberServiceMockTest {
    @Mock // 해당 필드의 객체를 Mock 객체로 생성
    private MemberRepository memberRepository;

    @InjectMocks // @InjectMocks 를 추가한 필드에 생성한 Mock 객체를 주입
    private MemberService memberService; // memberService 객체는 주입 받은 memberRepository Mock 객체를 포함

    @Test
    public void createMemberTest() {
        // given
        Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");

        // memberRepository Mock 객체로 Stubbing
        given(memberRepository.findByEmail(member.getEmail()))
                .willReturn(Optional.of(member)); // 테스트 결과 : passed

        // when, then
        /*
         Optional.of(member)의 member 객체에 포함된 이메일 주소가
         memberService.createMember(member)에서 피라미터로 전달한 member 객체에 포함된 이메일 주소와 동일

         Optional.of(member) 이메일 주소 == memberService.createMember(member) 이메일 주소
         -> 테스트 결과 : passed
         */
        assertThrows(BusinessLogicException.class, () -> memberService.createMember(member));
    }
}
