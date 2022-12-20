package com.codestates.slice.controller.member;

import com.codestates.member.dto.MemberDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest1 {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    void postMemberTest() throws Exception {
        // given
        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com", "홍길동", "010-1234-5678");

        String content = gson.toJson(post); // MemberDto.Post 객체를 JSON 포맷으로 변환

        // when
        ResultActions actions = mockMvc.perform( // 테스트 대상 Controller의 핸들러 메서드에 요청을 전송하기 위함 ( perform() )
                post("/v11/members") // HTTP POST METHOD, request URL 설정
                        .accept(MediaType.APPLICATION_JSON) // 클라이언트에서 리턴 받을 응답 데이터 타입으로 JSON 타입 설정
                        .contentType(MediaType.APPLICATION_JSON) // 서버 쪽에서 처리 가능한 Content 타입으로 JSON 타입 설정
                        .content(content) // request body 데이터 설정
        );

        // then
        MvcResult result = actions
                // andExpect() : 파라미터로 입력한 Matcher로 예상되는 기대 결과를 검증
                .andExpect(status().isCreated()) // status().isCreated() : response status가 201(Created)인지 매치 (회원 정보가 잘 생성/저장되었는지 검증)
//                .andExpect(header().string("Location", is(startsWith("/v11/members/")))); // HTTP header에 추가된 Location의 문자열 값이 “/v11/members/”로 시작하는지 검증
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
