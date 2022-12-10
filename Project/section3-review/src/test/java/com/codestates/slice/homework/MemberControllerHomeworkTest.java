//package com.codestates.slice.homework;
//
//import com.codestates.helper.MemberControllerTestHelper;
//import com.codestates.member.dto.MemberDto;
//import com.codestates.member.entity.Member;
//import com.codestates.member.repository.MemberRepository;
//import com.codestates.stamp.Stamp;
//import com.google.gson.Gson;
//import com.jayway.jsonpath.JsonPath;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.startsWith;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//// TODO MemberRepository를 이용해 테스트 케이스 실행 전에 테스트 데이터를 저장함
//@Transactional // 하나의 테스트가 끝나면 매번 rollback 처리를 함
//@SpringBootTest
//@AutoConfigureMockMvc
//public class MemberControllerHomeworkTest implements MemberControllerTestHelper {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void postMemberTest() throws Exception {
//        // given
//        // MemberController의 postMember()를 테스트를 위한 테스트 데이터 생성
//        MemberDto.Post post = new MemberDto.Post("hgd@gmail.com","홍길동","010-1111-1111");
//        String content = gson.toJson(post);
//        URI uri = UriComponentsBuilder.newInstance().path("/v11/members").build().toUri();
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                    post(uri)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(content));
//
//        // then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(header().string("Location", is(startsWith("/v11/members/"))));
//    }
//
//    @Test
//    void patchMemberTest() throws Exception {
//        // given
//        // MemberRepository를 이용하여 테스트 데이터를 생성하고 DB에 저장
//        Member member = new Member("hgd@gmail.com","홍길동","010-1111-1111");
//        member.setStamp(new Stamp());
//
//        Member resultMember = memberRepository.save(member);
//
//        long memberId = resultMember.getMemberId();
//
//        // 테스트 데이터 생성 후, DB에 업데이트
//        MemberDto.Patch patch = new MemberDto.Patch(1, null, "010-2222-2222", null);
//
//        String patchContent = gson.toJson(patch);
//
//        URI patchUri = UriComponentsBuilder.newInstance().path("/v11/members/{member-id}").buildAndExpand(memberId).toUri();
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                    patch(patchUri)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(patchContent));
//
//        // then
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.phone").value(patch.getPhone()));
//
//    }
//
//    @Test
//    void getMemberTest() throws Exception {
//        // given
//        // MemberRespository를 이용하여 테스트 데이터를 생성 후, DB에 저장
//        Member member = new Member("hgd@gmail.com","홍길동","010-1111-1111");
//        member.setStamp(new Stamp());
//
//        Member resultMember = memberRepository.save(member);
//        long memberId = resultMember.getMemberId();
//
//        URI getUri = UriComponentsBuilder.newInstance().path("/v11/members/{member-id}").buildAndExpand(memberId).toUri();
//
//        // when, then
//        mockMvc.perform(
//                    get(getUri)
//                            .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.email").value(resultMember.getEmail()))
//                .andExpect(jsonPath("$.data.name").value(resultMember.getName()))
//                .andExpect(jsonPath("$.data.phone").value(resultMember.getPhone()));
//    }
//
//    @Test
//    void getMembersTest() throws Exception {
//        // given
//        // MemberRespository를 이용하여 테스트 데이터를 생성 후, DB에 저장
//        Member member1 = new Member("hgd1@gmail.com","홍길동1","010-1111-1111");
//        member1.setStamp(new Stamp());
//        Member resultMember1 = memberRepository.save(member1);
//
//        Member member2 = new Member("hgd2@gmail.com","홍길동2","010-2222-2222");
//        member2.setStamp(new Stamp());
//        Member resultMember2 = memberRepository.save(member2);
//
//        String page = "1";
//        String size = "10";
//
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//        queryParams.add("page", page);
//        queryParams.add("size", size);
//
//        URI getUri = UriComponentsBuilder.newInstance().path("/v11/members").build().toUri();
//
//        // when
//        ResultActions actions =
//                mockMvc.perform(
//                        get(getUri)
//                                .params(queryParams)
//                                .accept(MediaType.APPLICATION_JSON)
//                );
//
//        // then
//        MvcResult result = actions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").isArray())
//                .andReturn();
//
//        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
//
//        assertThat(list.size(), is(2));
//    }
//
//    @Test
//    void deleteMemberTest() throws Exception {
//        // given
//        // MemberRespository를 이용하여 테스트 데이터를 생성 후, DB에 저장
//        Member member = new Member("hgd@gmail.com","홍길동","010-1111-1111");
//        member.setStamp(new Stamp());
//
//        Member resultMember = memberRepository.save(member);
//        long memberId = resultMember.getMemberId();
//
//        URI uri = UriComponentsBuilder.newInstance().path("/v11/members/{member-id}").buildAndExpand(memberId).toUri();
//
//        // when, then
//        mockMvc.perform(delete(uri))
//                .andExpect(status().isNoContent());
//    }
//}
