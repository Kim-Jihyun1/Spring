//package com.codestates.example.stub_example.stub_advice;
//
//import com.codestates.dto.SingleResponseDto;
//import com.codestates.member.dto.MemberDto;
//import com.codestates.member.entity.Member;
//import com.codestates.stamp.Stamp;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class MemberStubAdvice {
//    @Pointcut(
//            "execution(* com.codestates.example.stub_example.stub_advice.MemberControllerV13.postMember(..))"
//
//    )
//    public void postMemberMethod(){}
//
//    @Around(value = "postMemberMethod()")
//    public ResponseEntity aroundPostMember(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("### Apply Around Join Point");
//        MemberDto.response response = (MemberDto.response) joinPoint.proceed();
//        response = new MemberDto.response(1L,
//                "hgd3@gmail.com",
//                "Hong Gil Dong",
//                "010-3333-3333",
//                Member.MemberStatus.MEMBER_ACTIVE,
//                new Stamp());
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(response), HttpStatus.CREATED);
//    }
//}
