package com.nighthawk.spring.mvc.income;

import jakarta.persistence.Basic;

// import java.util.Date;

// import jakarta.persistence.*;
import jakarta.persistence.FetchType;
// import jakarta.persistence.CascadeType;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @JoinColumn(name = "user_id") // i dont get it
    // @ManyToOne(cascade = CascadeType.MERGE) // holds reference
    // private User user;

    // @ManyToOne(cascade = CascadeType.MERGE, optional = false) // holds reference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false) // this sets user id just fill it bro
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Income income;

    // TODO: add annotations
    @Basic(optional = true)
    @Column(nullable = true)
    private double salary;

    @Basic(optional = true)
    @Column(nullable = true)
    private double investments;

    @Basic(optional = true)
    @Column(nullable = true)
    private double allowance;

    @Basic(optional = true)
    @Column(nullable = true)
    private double miscellaneous;

    public Income(double salary, double investments, double allowance, double miscellaneous) {
        // this.id = id;
        // user.setId(id);
        // this.id = id;
        this.salary = salary;
        this.investments = investments;
        this.allowance = allowance;
        this.miscellaneous = miscellaneous;
    }
    public void setIncome(Income i) {
        this.income = i;
    }
    public double calculateIncome () {
        // double dSalary = Double.parseDouble(sal);
        // double dInvestments = Double.parseDouble(inv);
        // double dAllowance = Double.parseDouble(all);
        // double dMiscellaneous = Double.parseDouble(misc);

        return this.salary + this.investments + this.allowance + this.miscellaneous;
    }
}