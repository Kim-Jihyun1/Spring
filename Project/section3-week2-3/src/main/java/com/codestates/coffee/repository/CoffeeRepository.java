package com.codestates.coffee.repository;

import com.codestates.coffee.entity.Coffee;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    // TODO 쿼리 메서드 정의
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    @Query("SELECT * FROM COFFEE WHREW COFFEE_ID = :coffeeId")
    Optional<Coffee> findByCoffee(Long coffeeId);
}
