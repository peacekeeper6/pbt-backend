package com.nighthawk.spring.mvc.budgeting;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import com.nighthawk.spring.mvc.race.Race;
import com.nighthawk.spring.mvc.user.User;

import java.util.List;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve database
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve database from relational databases to Java objects and vice versa.
 */

public interface BudgetingJpaRepository extends JpaRepository<Budgeting, Long> {
    List<Budgeting> findAllByOrderByIdAsc(); // for now return a list, its concatenated and users have ability to expand if need be
    // I want this to return everything 
    
    // List<Dashboard> findAllByRace(Race race);

    // List<Dashboard> findAllById(Long id);

    List<Budgeting> findAllByUser(User user);
    
    Budgeting findByShopping(double shopping);

    Budgeting findByEating(double eating);

    Budgeting findByTravel(double subscriptions);

    Budgeting findByMiscellaneous(double miscellaneous);


    // Dashboard findByRaceAndUserAndBetActive(Race race, User user, Boolean betActive);

    // // Custom JPA query
    // @Query(value = "SELECT * FROM Person p WHERE p.name LIKE ?1 or p.email LIKE ?1", nativeQuery = true)
    // List<Dashboard> findByLikeTermNative(String term);
}
