package com.nighthawk.spring_portfolio.mvc.budgeting;

import javax.persistence.Basic;

// import java.util.Date;

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
public class Budgeting {
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
    private String shopping;

    @Basic(optional = true)
    @Column(nullable = true)
    private String eating;

    @Basic(optional = true)
    @Column(nullable = true)
    private String subscriptions;

    @Basic(optional = true)
    @Column(nullable = true)
    private String travel;

    @Basic(optional = true)
    @Column(nullable = true)
    private String miscellaneous;

    public Budgeting(Long id, String shopping, String eating, String subscriptions, String travel, String miscellaneous) {
        this.id = id;
        this.shopping = shopping;
        this.eating = eating;
        this.subscriptions = subscriptions;
        this.travel = travel;
        this.miscellaneous = miscellaneous;
    }

    public double calculateBudgeting (String shop, String eat, String sub, String tra, String mis) {
        // parse Strings to doubles
        double dShopping = Double.parseDouble(shop);
        double dEating = Double.parseDouble(eat);
        double dSubscriptions = Double.parseDouble(sub);
        double dTravel = Double.parseDouble(tra);
        double dMiscellaneous = Double.parseDouble(mis);
        
        return dShopping + dEating + dSubscriptions + dTravel + dMiscellaneous;
    }

    // public static void main(String args[]) {
    //     Budgeting a = new Budgeting();
    //     a.calculateBudgeting(1, 2.3, 2, 2, 1);
    // }


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