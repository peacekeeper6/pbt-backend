package com.nighthawk.spring_portfolio.mvc.expenses;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.validation.constraints.PositiveOrZero;

// import org.springframework.format.annotation.DateTimeFormat;

// import com.nighthawk.spring_portfolio.mvc.race.Race;
// import com.nighthawk.spring_portfolio.mvc.team.Team;
import com.nighthawk.spring_portfolio.mvc.user.User;

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

    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // private Date date;

    // @PositiveOrZero
    // private String fCoinBet;

    // private boolean betActive;

    @JoinColumn(name = "user_id") // i dont get it
    @ManyToOne(cascade = CascadeType.MERGE) // holds reference
    private User user;

    // TODO: add annotations
    @Basic(optional = true)
    @Column(nullable = true)
    private String groceries;

    @Basic(optional = true)
    @Column(nullable = true)
    private String transportation;

    @Basic(optional = true)
    @Column(nullable = true)
    private String education;

    @Basic(optional = true)
    @Column(nullable = true)
    private String housing;

    @Basic(optional = true)
    @Column(nullable = true)
    private String shopping;

    @Basic(optional = true)
    @Column(nullable = true)
    private String utilities;

    @Basic(optional = true)
    @Column(nullable = true)
    private String insurance;

    @Basic(optional = true)
    @Column(nullable = true)
    private String personal;

    @Basic(optional = true)
    @Column(nullable = true)
    private String subscriptions;

    @Basic(optional = true)
    @Column(nullable = true)
    private String investments;

    @Basic(optional = true)
    @Column(nullable = true)
    private String miscellaneous;
    
    public Expenses (Long id, String groceries, String transportation, String education, String housing, String shopping, 
    String utilities, String insurance, String personal, String subscriptions, String investments, String miscellaneous) {
        this.id = id;
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

    public double calculateExpenses (String gro, String trans, String edu, String hou, String sho, String util, String ins, 
    String pers, String subs, String inves, String misce) {
        double dGroceries = Double.parseDouble(gro);
        double dTransportation = Double.parseDouble(trans);
        double dEducation = Double.parseDouble(edu);
        double dHousing = Double.parseDouble(hou);
        double dShopping = Double.parseDouble(sho);
        double dUtilities = Double.parseDouble(util);
        double dInsurance = Double.parseDouble(ins);
        double dPersonal = Double.parseDouble(pers);
        double dSubscriptions = Double.parseDouble(subs);
        double dInvestments = Double.parseDouble(inves);
        double dMiscellaneous = Double.parseDouble(misce);

        return dGroceries + dTransportation + dEducation + dHousing + dShopping + dUtilities + dInsurance + dPersonal + 
        dSubscriptions + dInvestments + dMiscellaneous;
    }


    // @JoinColumn(name = "team_id")
    // @ManyToOne(cascade = CascadeType.MERGE)
    // private Team team;

    // @JoinColumn(name = "race_id")
    // @ManyToOne(cascade = CascadeType.MERGE)
    // private Race race;

    // public Bet(String fCoinBet, Date date) {
    //     this.fCoinBet = fCoinBet;
    //     this.date = date;

    //     // TODO: gotta do check for current day and make false if date passed
    //     this.betActive = true;
    // }

    // public boolean getBetActive() {
    //     return this.betActive;
    // }

    // public String toString() {
    // return ("{ \"raceName\": " + this.race.getName() + ", " + "\"team\": " +
    // this.team.getName() + ", "
    // + "\"fCoinBet\": " + this.fCoinBet + " }");
    // }
}