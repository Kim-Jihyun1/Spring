package com.codestates.slice.repository.member;

import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// @DataJpaTest : 데이터 액세스 계층에 필요한 자동 구성을 활성화
@DataJpaTest // @Transactional 을 포함하고 있어, 하나의 테스트 케이스 실행이 종료되는 시점에 DB에 저장된 데이터는 rollback
public class MemberRepositoryTest {
    // DI
    @Autowired
    private MemberRepository memberRepository; //

    @Test
    public void saveMemberTest() {
        // given
        Member member = new Member();
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setPhone("010-1111-2222");

        // when
        // 회원 정보 저장
        Member savedMember = memberRepository.save(member);

        // then
        assertNotNull(savedMember); // 회원 정보를 정상적으로 저장한 후, 리턴 값으로 반환된 Member 객체(savedMember)가 null이 아닌지를 검증
        // savedMember의 필드가 테스트 데이터와 일치하는지 검증
        assertTrue(member.getEmail().equals(savedMember.getEmail()));
        assertTrue(member.getName().equals(savedMember.getName()));
        assertTrue(member.getPhone().equals(savedMember.getPhone()));
    }

    @Test
    public void findByEmailTest() {
        // given
        Member member = new Member();
        member.setEmail("hgd@gmail.com");
        member.setName("홍길동");
        member.setPhone("010-1111-2222");

        // when
        // 회원 정보 저장
        memberRepository.save(member);

        // 저장한 회원 정보 중, 이메일에 해당하는 회원 정보를 잘 조회하는지 테스트
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail()); // findByEmail()로 회원 정보 조회

        // then
        // 회원 정보의 조회가 정상적으로 이루어지는지 검증
        assertTrue(findMember.isPresent()); // 조회된 회원 정보가 null이 아닌지 검증
        assertTrue(findMember.get().getEmail().equals(member.getEmail())); // 조회한 회원의 이메일 주소 == 테스트 데이터의 이메일이 일치하는지 검증
    }
}
