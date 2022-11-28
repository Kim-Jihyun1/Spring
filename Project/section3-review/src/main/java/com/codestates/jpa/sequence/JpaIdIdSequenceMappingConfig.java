package com.codestates.jpa.sequence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaIdIdSequenceMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory) {
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();
            em.persist(new Member());  // 별도의 기본키 값을 전달하지 않음 ->  SEQUENCE 전략을 사용 (엔티티가 영속성 컨텍스트에 저장되기 전, DB가 시퀀스에서 기본키에 해당하는 값을 제공)
            Member member = em.find(Member.class, 1L);
            System.out.println("# memberId: " + member.getMemberId());
            tx.commit();

        };
    }
}
