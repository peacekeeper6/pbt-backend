package com.nighthawk.spring.mvc.expenses;

// import java.util.Date;

import jakarta.persistence.Basic;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expenses {
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
    private double groceries;

    @Basic(optional = true)
    @Column(nullable = true)
    private double transportation;

    @Basic(optional = true)
    @Column(nullable = true)
    private double education;

    @Basic(optional = true)
    @Column(nullable = true)
    private double housing;

    @Basic(optional = true)
    @Column(nullable = true)
    private double shopping;

    @Basic(optional = true)
    @Column(nullable = true)
    private double utilities;

    @Basic(optional = true)
    @Column(nullable = true)
    private double insurance;

    @Basic(optional = true)
    @Column(nullable = true)
    private double personal;

    @Basic(optional = true)
    @Column(nullable = true)
    private double subscriptions;

    @Basic(optional = true)
    @Column(nullable = true)
    private double investments;

    @Basic(optional = true)
    @Column(nullable = true)
    private double miscellaneous;
    
    public Expenses (User user, double groceries, double transportation, double education, double housing, double shopping, 
    double utilities, double insurance, double personal, double subscriptions, double investments, double miscellaneous) {
        // this.id = id;
        // user.setId(id);
        this.user = user;
        this.groceries = groceries;
        this.transportation = transportation;
        this.education = education;
        this.housing = housing;
        this.shopping = shopping;
        this.utilities = utilities;
        this.insurance = insurance;
        this.personal = personal;
        this.subscriptions = subscriptions;
        this.investments = investments;
        this.miscellaneous = miscellaneous;
    }

    public double calculateExpenses () {
        // double dGroceries = Double.parseDouble(gro);
        // double dTransportation = Double.parseDouble(trans);
        // double dEducation = Double.parseDouble(edu);
        // double dHousing = Double.parseDouble(hou);
        // double dShopping = Double.parseDouble(sho);
        // double dUtilities = Double.parseDouble(util);
        // double dInsurance = Double.parseDouble(ins);
        // double dPersonal = Double.parseDouble(pers);
        // double dSubscriptions = Double.parseDouble(subs);
        // double dInvestments = Double.parseDouble(inves);
        // double dMiscellaneous = Double.parseDouble(misce);

        return this.groceries + this.transportation + this.education + this.housing + this.shopping + 
        this.utilities + this.insurance + this.personal + this.subscriptions + this.investments + this.miscellaneous;
    }
}