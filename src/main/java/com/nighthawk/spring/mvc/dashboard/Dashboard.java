package com.nighthawk.spring.mvc.dashboard;

import jakarta.persistence.Basic;

// import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nighthawk.spring.mvc.user.User;
import com.nighthawk.spring.mvc.income.Income;
import com.nighthawk.spring.mvc.expenses.Expenses;
import com.nighthawk.spring.mvc.budgeting.Budgeting;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dashboard {
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false) // holds reference
    @JoinColumn(name = "user_id") // this sets user id just fill it bro
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    @ManyToOne(cascade = CascadeType.MERGE, optional = false) // holds reference
    @JoinColumn(name = "user_id") // this sets user id just fill it bro
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Budgeting budgeting;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false) // holds reference
    @JoinColumn(name = "user_id") // this sets user id just fill it bro
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Income income;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false) // holds reference
    @JoinColumn(name = "user_id") // this sets user id just fill it bro
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Expenses expenses;
    
    // // TODO: add annotations
    // @Basic(optional = true)
    // @Column(nullable = true)
    // private double income;

    // @Basic(optional = true)
    // @Column(nullable = true)
    // private double expenses;

    // @Basic(optional = true)
    // @Column(nullable = true)
    // private double budgeting;

    // public Dashboard(double income, double expenses, double budgeting) {
    //     this.income = income;
    //     this.expenses = expenses;
    //     this.budgeting = budgeting;
    // }

    // public double getMoneyYeah(double in, double out, double budget) { // where to use method?
    //     // double dIncome = Double.parseDouble(in);
    //     // double dExpenses = Double.parseDouble(out);
    //     // double dBudgeting = Double.parseDouble(budget);

    //     return (this.income + this.budgeting) - this.expenses;
    // }
}