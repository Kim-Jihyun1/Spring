package com.codestates.slice.mock.homework;

import com.codestates.helper.MemberControllerTestHelper;
import com.codestates.helper.StubData;
import com.codestates.member.dto.MemberDto;
import com.codestates.member.entity.Member;
import com.codestates.member.mapper.MemberMapper;
import com.codestates.member.service.MemberService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 리팩토링 후 (중복 제거)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerHomeworkTest2 implements MemberControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Test
    void postMemberTest() throws Exception {
        // given
        // StubData 클래스를 이용하여 매번 테스트 데이터를 정의하는 중복을 제거함
        MemberDto.Post post = (MemberDto.Post) StubData.MockMember.getRequestBody(HttpMethod.POST);
        MemberDto.response responseBody = StubData.MockMember.getSingleResponseBody();

        // Stubbing by Mockito
        given(mapper.memberPostDtoToMember(Mockito.any(MemberDto.Post.class)))
                .willReturn(new Member());
        given(memberService.createMember(Mockito.any(Member.class)))
                .willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class)))
                .willReturn(responseBody);

        String content = toJsonContent(post);
        URI uri = getURI();

        // when
        ResultActions actions = mockMvc.perform(postRequestBuilder(uri, content));

        // then
        MvcResult result = actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(post.getEmail()))
                .andExpect(jsonPath("$.data.name").value(post.getName()))
                .andExpect(jsonPath("$.data.phone").value(post.getPhone()))
                .andReturn();

//        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void patchMemberTest() throws Exception {
        // given
        long memberId = 1L;

        MemberDto.Patch patch = (MemberDto.Patch) StubData.MockMember.getRequestBody(HttpMethod.PATCH);

        MemberDto.response response =
                StubData.MockMember.getSingleResponseBody(null, "010-2222-2222", null);


        // Stubbing by Mockito
        given(mapper.memberPatchDtoToMember(Mockito.any(MemberDto.Patch.class)))
                .willReturn(new Member());
        given(memberService.updateMember(Mockito.any(Member.class)))
                .willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class)))
                .willReturn(response);

        String content = toJsonContent(patch);
        URI uri = getURI(memberId);

        // when
        ResultActions actions = mockMvc.perform(patchRequestBuilder(uri, content));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()));
    }
    void getMemberTest() throws Exception {
        // given
        long memberId = 1L;
        Member member = StubData.MockMember.getSingleResultMember(memberId);
        MemberDto.response response = StubData.MockMember.getSingleResponseBody();

        // Stubbing by Mockito
        given(memberService.findMember(Mockito.anyLong()))
                .willReturn(new Member());
        given(mapper.memberToMemberResponseDto(Mockito.any(Member.class)))
                .willReturn(response);

        URI uri = getURI(memberId);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(uri));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.name").value(member.getName()))
                .andExpect(jsonPath("$.data.phone").value(member.getPhone()));
    }

    @Test
    void getMembersTest() throws Exception {
        // given
        Page<Member> pageMembers = StubData.MockMember.getMultiResultMember();

        List<MemberDto.response> responses = StubData.MockMember.getMultiResponseBody();

        // Stubbing by Mockito
        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt()))
                .willReturn(pageMembers);
        given(mapper.membersToMemberResponseDtos(Mockito.anyList()))
                .willReturn(responses);

        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        URI uri = getURI();

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(uri, queryParams));

        // then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        System.out.println(list);

        assertThat(list.size(), is(2));
    }

    @Test
    void deleteMemberTest() throws Exception {
        // given
        long memberId = 1L;

        // Stubbing by Mockito
        doNothing().when(memberService).deleteMember(memberId);

        // when
        ResultActions actions = mockMvc.perform(deleteRequestBuilder(getURI(memberId)));

        // then
        actions.andExpect(status().isNoContent());
    }
}
