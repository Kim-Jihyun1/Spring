package com.codestates.coffee.repository;

import com.codestates.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    // JPQL 사용
    @Query(value = "SELECT c FROM Coffee c WHERE c.coffeeId = :coffeeId")  // Coffee는 클래스명, coffeeId는 Coffee 클래스의 필드명
    Optional<Coffee> findByCoffee(long coffeeId);
}
