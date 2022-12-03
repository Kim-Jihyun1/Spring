package com.codestates.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;
import java.util.HashMap;
import java.util.Map;

@Configuration // Configuration 클래스 정의
public class TxConfig {
    private final TransactionManager transactionManager;

    // TransactionManager 객체를 DI
    public TxConfig(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // TransactionInterceptor 빈 등록 (대상 클래스/인터페이스에 트랜잭션 경계 설정 및 적용)
    @Bean
    public TransactionInterceptor txAdvice() {
        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();

        // 조회 메서드를 제외한 공통 트랜잭션 애트리뷰트
        RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();
        txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 조회 메서드에 적용할 트랜잭션 애트리뷰트
        RuleBasedTransactionAttribute txFindAttribute = new RuleBasedTransactionAttribute();
        txFindAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txFindAttribute.setReadOnly(true);

        // 트랜잭션 애트리뷰트 매핑

        // Map의 key를 '메서드명 패턴'으로 지정하여 각각의 트랜잭션 애트리뷰트를 추가
        Map<String, TransactionAttribute> txMethods = new HashMap<>();
        txMethods.put("find*", txFindAttribute);
        txMethods.put("*", txAttribute);

        // 트랜잭션 애트리뷰트를 추가한 Map 객체를 넘겨줌
        txAttributeSource.setNameMap(txMethods);

        // TransactionInterceptor 객체 생성
        return new TransactionInterceptor(transactionManager, txAttributeSource);
    }

    @Bean
    public Advisor txAdvisor() {
        // AspectJExpressionPointcut 객체 생성 후, 포인트컷 표현식으로 CoffeeService 클래스를 타겟으로 지정
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.codestates.coffee.service." + "CoffeeService.*(..))");

        // DefaultPointcutAdvisor의 생성자 파라미터로 포인트컷과 어드바이스 전달
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
