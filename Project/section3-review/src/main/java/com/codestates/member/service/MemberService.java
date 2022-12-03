package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import com.codestates.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional // 트랜잭션 적용
public class MemberService {
    private final MemberRepository memberRepository;
    private final CustomBeanUtils<Member> beanUtils;

    public MemberService(MemberRepository memberRepository, CustomBeanUtils<Member> beanUtils) {
        this.memberRepository = memberRepository;
        this.beanUtils = beanUtils;
    }

    public Member createMember(Member member) {
        // 이미 등록된 이메일인지 확인
        verifyExistsEmail(member.getEmail());
        Member resultMember = memberRepository.save(member);

        if (true) {
            throw new RuntimeException("Rollback test");
        }

        return resultMember;
    }

    // 리펙토링 전
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

    // 리펙토링 후
    @Transactional(propagation = Propagation.REQUIRED) // 현재 진행 중인 트랜잭션이 존재하면 해당 트랜잭션을 사용함 (존재하지 않으면 새 트랜잭션을 생성)
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

//        beanUtils.copyNonNullProperties(member, findMember);
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        return memberRepository.save(findMember);
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

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
