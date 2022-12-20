package com.codestates.helper.email;

import org.springframework.mail.MailSendException;

public class MockExceptionEmailSendable implements EmailSendable{
    @Override
    public void send(String message) throws InterruptedException {
        Thread.sleep(5000L); // 5초 뒤에 이메일 전송 실패 예외를 던짐
        throw new MailSendException("error while send email");
    }
}
