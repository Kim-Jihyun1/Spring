package com.codestates.jpa.basic;

import com.codestates.member.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaBasicConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaBasicRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            example05();
        };
    }

    // TODO 영속성 컨텍스트에 Member 객체 저장
//    public void example01() {
//        Member member = new Member("hgd@gmai.com");
//
//        em.persist(member); // member 객체를 영속성 켄텍스트에 저장
//
//        // 영속성 컨텍스트에 저장된 member 객체 조회
//        Member resultMember = em.find(Member.class, 1L); // find(조회할 엔티티 클래스 타입, 식별자 값)
//        System.out.println("Id: " + resultMember.getMemberId() + ", email: " + resultMember.getEmail());
//    }

    // TODO 영속성 컨텍스트와 테이블에 Member 객체 저장
//    public void example02() {
//        // Transaction 시작하기 위함
//        tx.begin();
//        Member member = new Member("hgd@gmail.com");
//
//        em.persist(member); // member 객체를 영속성 켄텍스트에 저장
//
//        tx.commit(); // 영속성 컨텍스트에 저장된 member 객체를 DB 테이블에 저장
//
//        Member resultMember1 = em.find(Member.class, 1L);
//        System.out.println("Id: " + resultMember1.getMemberId() + ", email: " + resultMember1.getEmail());
//
//        Member resultMember2 = em.find(Member.class, 2L);
//        System.out.println(resultMember2 == null);
//    }

    // TODO 쓰기 지연을 통한 Member 객체 저장
//    public void example03() {
//        tx.begin();
//
//        Member member1 = new Member("hgd1@gmail.com");
//        Member member2 = new Member("hgd2@gmail.com");
//
//        // member1, 2를 영속성 컨텍스트에 저장
//        em.persist(member1);
//        em.persist(member2);
//
//        tx.commit();
//    }

    // TODO Member 객체 업데이트
//    public void example04() {
//        tx.begin();
//        em.persist(new Member("hgd@gmail.com")); // 영속성 컨텍스트의 1차 캐시에 저장
//        tx.commit(); // 영속성 컨텍스트의 쓰기 지연 SQL 저장소에서 등록된 INSERT 쿼리 실행
//
//        tx.begin();
//        Member member1 = em.find(Member.class, 1L); // 저장된 member 객체를 영속성 컨텍스트의 1차 캐시에서 조회
//        member1.setEmail("hgd@yahoo.co.kr"); // setter 메서드로 이메일 정보 변경
//        tx.commit(); // 쓰기 지연 SQL 저장소에 등록된 UPDATE 쿼리 실행
//    }

    // TODO Member 객체 삭제
    public void example05() {
        tx.begin();
        em.persist(new Member("hgd1@gmail.com")); // 영속성 컨텍스트의 1차 캐시에 저장
        tx.commit(); // 쓰기 지연 SQL 저장소에 등록된 INSERT 쿼리 실행

        tx.begin();
        Member member = em.find(Member.class, 1L);   // 영속성 컨텍스트의 1차 캐시에서 조회
        em.remove(member);     // 영속성 컨텍스트의 1차 캐시에 있는 엔티티 제거 요청
        tx.commit();     // 엔티티 제거 후, 쓰기 지연 SQL 저장소에 등록된 DELETE 쿼리 실행
    }
    // tx.commit() 메서드가 호출되면 JPA의 내부적으로 em.flush() 메서드가 호출되고, 영속성 컨텍스트의 변경 내용을 DB에 반영함
}
