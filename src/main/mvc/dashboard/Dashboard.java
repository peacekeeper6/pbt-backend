package com.nighthawk.spring_portfolio.mvc.dashboard;

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
public class Dashboard {
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
    private String income;

    @Basic(optional = true)
    @Column(nullable = true)
    private String expenses;

    @Basic(optional = true)
    @Column(nullable = true)
    private String budgeting;

    public double getMoneyYeah(String in, String out, String budget) { // where to use method?
        double dIncome = Double.parseDouble(in);
        double dExpenses = Double.parseDouble(out);
        double dBudgeting = Double.parseDouble(budget);

        return (dIncome + dExpenses) - dExpenses;
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