package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.helper.event.MemberRegistrationEvent;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.utils.CustomBeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // 트랜잭션 적용
public class MemberService {
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher publisher;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository, ApplicationEventPublisher publisher, CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.publisher = publisher;
        this.beanUtils = beanUtils;
    }

    @Transactional
    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 확인 ( == DB에 존재하는 이메일인지 검증)
        verifyExistsEmail(member.getEmail());
        Member savedMember = memberRepository.save(member);

        publisher.publishEvent(new MemberRegistrationEvent(savedMember));

        return savedMember;
    }

    public Member updateMemberOld(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        // 수정할 정보가 늘어날 수록 중복 코드가 늘어남
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        // 회원의 상태 정보 업데이트
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        // 수정 시간 업데이트
//        findMember.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(findMember);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE) // 현재 진행 중인 트랜잭션이 존재하면 해당 트랜잭션을 사용함 (존재하지 않으면 새 트랜잭션을 생성)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        // 리팩토링 전
//        Optional.ofNullable(member.getName())
//                .ifPresent(name -> findMember.setName(name));
//        Optional.ofNullable(member.getPhone())
//                .ifPresent(phone -> findMember.setPhone(phone));
//        Optional.ofNullable(member.getMemberStatus())
//                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        // 리팩토링 후
        Member updateMember = beanUtils.copyNonNullProperties(member, findMember);

        return memberRepository.save(updateMember);
    }

    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(
                PageRequest.of(page, size,
                        Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    private void verifyExistsEmail(String email) {
        // verifyExistsEmail() 메서드의 파라미터 email 전달 -> memberRepository.findByEmail(email)을 통해 DB에서 조회
        // email을 조건으로 한 회원 정보가 있는지를 DB에서 조회
        Optional<Member> member = memberRepository.findByEmail(email);

        // member 객체의 null 여부를 판단하는 비즈니스 로직
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
