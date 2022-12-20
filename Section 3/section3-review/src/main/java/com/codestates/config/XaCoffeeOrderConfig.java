//package com.codestates.config;
//
//import com.atomikos.jdbc.AtomikosDataSourceBean;
//import com.mysql.cj.jdbc.MysqlXADataSource;
//import org.hibernate.engine.transaction.jta.platform.internal.AtomikosJtaPlatform;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//// JpaRepository 활성화
//@EnableJpaRepositories(
//        // basePackages : 기존에 사용하던 JpaRepository를 그대로 사용하도록 해당 Repository 패키지 경로를 작성
//        basePackages = {"com.codestates.member", "com.codestates.order", "com.codestates.coffee"},
//        entityManagerFactoryRef = "coffeeOrderEntityManager")  // entityManagerFactoryRef : EntityManagerFactoryBean의 메서드명
//@Configuration
//public class XaCoffeeOrderConfig {
//    // 데이터 소스 생성 (eb 접속 정보 설정)
//    @Primary
//    @Bean
//    public DataSource dataSourceCoffeeOrder() {
//        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
//        mysqlXADataSource.setURL(
//                "jdbc:mysql://localhost:3306/coffee_order" +
//                "?allowPublicKeyRetrieval=true" +
//                "&characterEncoding=UTF-8");
//        mysqlXADataSource.setUser("guest");
//        mysqlXADataSource.setPassword("guest");
//
//        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
//        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
//        atomikosDataSourceBean.setUniqueResourceName("xaCoffeeOrder");
//
//        return atomikosDataSourceBean;
//    }
//
//    // EntityManagerFactoryBean 설정
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean coffeeOrderEntityManager() {
//        LocalContainerEntityManagerFactoryBean emFactoryBean = new LocalContainerEntityManagerFactoryBean();
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.MYSQL);
//
//        // Hibernate에서 필요한 설정 정보
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "create");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//
//        // JTA Platform 이름 추가
//        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
//        properties.put("javax.persistence.transactionType", "JTA");
//
//        emFactoryBean.setDataSource(dataSourceCoffeeOrder());
//        emFactoryBean.setPackagesToScan(new String[]{
//                "com.codestates.member", "com.codestates.stamp", "com.codestates.order", "com.codestates.coffee"
//        });
//        emFactoryBean.setJpaVendorAdapter(vendorAdapter);
//        emFactoryBean.setPersistenceUnitName("coffeeOrderPersistenceUnit");
//        emFactoryBean.setJpaPropertyMap(properties);
//
//        return emFactoryBean;
//    }
//}
