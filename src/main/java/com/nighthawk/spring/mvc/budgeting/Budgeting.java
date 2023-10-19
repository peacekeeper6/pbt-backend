package com.nighthawk.spring.mvc.budgeting;

import jakarta.persistence.Basic;

// import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nighthawk.spring.mvc.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Budgeting {
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false) // holds reference
    @JoinColumn(name = "user_id") // this sets user id just fill it bro
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    // TODO: add annotations
    @Basic(optional = true)
    @Column(nullable = true)
    private double shopping;

    @Basic(optional = true)
    @Column(nullable = true)
    private double eating;

    @Basic(optional = true)
    @Column(nullable = true)
    private double subscriptions;

    @Basic(optional = true)
    @Column(nullable = true)
    private double travel;

    @Basic(optional = true)
    @Column(nullable = true)
    private double miscellaneous;


    // User userid = new User();
    // User userid = // Retrieve or create a User entity
    // userid.setUser(userid);
    // entityManager.persist(userid);

    public Budgeting(User user, double shopping, double eating, double subscriptions, double travel, double miscellaneous) {
        this.user = user;
        this.shopping = shopping;
        this.eating = eating;
        this.subscriptions = subscriptions;
        this.travel = travel;
        this.miscellaneous = miscellaneous;
    }

    public double calculateBudgeting() {
        // parse doubles to doubles
        // double dShopping = Double.parseDouble(shop);
        // double dEating = Double.parseDouble(eat);
        // double dSubscriptions = Double.parseDouble(sub);
        // double dTravel = Double.parseDouble(tra);
        // double dMiscellaneous = Double.parseDouble(mis);
        
        return this.shopping + this.eating + this.subscriptions + this.travel + this.miscellaneous;
    }

    // public static void main(double args[]) {
    //     Budgeting a = new Budgeting();
    //     a.calculateBudgeting(1, 2.3, 2, 2, 1);
    // }
}