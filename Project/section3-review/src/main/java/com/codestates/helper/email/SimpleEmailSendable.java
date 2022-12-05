package com.codestates.helper.email;

public class SimpleEmailSendable implements EmailSendable {
    @Override
    public void send(String message) {
        // 문자열 형태의 이메일 구현 로직 구성
        System.out.println("Sent simple email!");
    }
}
