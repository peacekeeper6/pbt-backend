package com.nighthawk.spring.mvc.expenses;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nighthawk.spring.mvc.user.User;

import java.util.List;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve database
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve database from relational databases to Java objects and vice versa.
 */

public interface ExpensesJpaRepository extends JpaRepository<Expenses, Long> {
    List<Expenses> findAllByOrderByIdAsc(); // for now return a list, its concatenated and users have ability to expand if need be
    // I want this to return everything 
    
    // List<Dashboard> findAllByRace(Race race);

    // List<Dashboard> findAllById(Long id);

    List<Expenses> findAllByUser(User user);
    
    Expenses findByGroceries(double groceries);

    Expenses findByTransportation(double transportation);

    Expenses findByEducation(double education);

    Expenses findByHousing(double housing);

    Expenses findByShopping(double shopping);

    Expenses findByUtilities(double utilities);

    Expenses findByInsurance(double insurance);

    Expenses findByPersonal(double personal);

    Expenses findBySubscriptions(double subscriptions);

    Expenses findByInvestments(double investments);

    Expenses findByMiscellaneous(double miscellaneous);

    // // Custom JPA query
    // @Query(value = "SELECT * FROM Person p WHERE p.name LIKE ?1 or p.email LIKE ?1", nativeQuery = true)
    // List<Dashboard> findByLikeTermNative(String term);
}
