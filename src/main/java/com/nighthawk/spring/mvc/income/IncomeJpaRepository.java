package com.nighthawk.spring.mvc.income;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.nighthawk.spring.mvc.user.User;


import java.util.List;
import jakarta.transaction.Transactional;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve database
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve database from relational databases to Java objects and vice versa.
 */

public interface IncomeJpaRepository extends JpaRepository<Income, Long> {
    // List<Income> findAllByOrderByUserAsc(User user); // for now return a list, its concatenated and users have ability to expand if need be
    // I want this to return everything 

    List<Income> findAllByUser(User user);
    
    Income findBySalary(double salary);

    Income findByInvestments(double investments);

    Income findByAllowance(double allowance);

    Income findByMiscellaneous(double miscellaneous);

    // List<Income> findByUserId(Long postId);
  
    @Transactional
    void deleteByUserId(Long userId);
    // Dashboard findByRaceAndUserAndBetActive(Race race, User user, Boolean betActive);

    // // Custom JPA query
    // @Query(value = "SELECT * FROM Person p WHERE p.name LIKE ?1 or p.email LIKE ?1", nativeQuery = true)
    // List<Dashboard> findByLikeTermNative(String term);
}