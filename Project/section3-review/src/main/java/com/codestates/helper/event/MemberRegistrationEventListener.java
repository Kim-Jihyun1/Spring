package com.codestates.helper.event;

import com.codestates.helper.email.EmailSender;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@Component
public class MemberRegistrationEventListener {
    // DI
    private final EmailSender emailSender;
    private final MemberService memberService;

    public MemberRegistrationEventListener(EmailSender emailSender, MemberService memberService) {
        this.emailSender = emailSender;
        this.memberService = memberService;
    }

    @Async
    @EventListener
    public void listen(MemberRegistrationEvent event) throws Exception {
        try {
            // 이메일 전송 시뮬레이션 (전송할 메시지를 생성했다고 가정)
            String message = "any email message";
            emailSender.sendEmail(message);
        }
        catch (MailSendException e) {
            e.printStackTrace();
            log.error("MailSendException: rollback for Member Registration: ");

            Member member = event.getMember();
            memberService.deleteMember(member.getMemberId());
        }
    }
}