package com.codestates.jpa.identity;

import com.codestates.member.entity.Member;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Configuration
public class JpaIdDirectMappingConfig {
    private EntityManager em;
    private EntityTransaction tx;

    @Bean
    public CommandLineRunner testJpaSingleMappingRunner(EntityManagerFactory emFactory){
        this.em = emFactory.createEntityManager();
        this.tx = em.getTransaction();

        return args -> {
            tx.begin();
            em.persist(new Member());  // Member 엔티티에  IDENTITY 전략이 적용되었으므로 별도의 기본키 값을 할당하지 않음
            tx.commit();
            Member member = em.find(Member.class, 1L);

            System.out.println("# memberId: " + member.getMemberId());
        };
    }
}
