package com.nighthawk.spring_portfolio.mvc.income;

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
public class Income {
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
    private String salary;

    @Basic(optional = true)
    @Column(nullable = true)
    private String investments;

    @Basic(optional = true)
    @Column(nullable = true)
    private String allowance;

    @Basic(optional = true)
    @Column(nullable = true)
    private String miscellaneous;

    public Income(Long id, String salary, String investments, String allowance, String miscellaneous) {
        this.id = id;
        this.salary = salary;
        this.investments = investments;
        this.allowance = allowance;
        this.miscellaneous = miscellaneous;
    }

    public double calculateIncome (String sal, String inv, String all, String misc) {
        double dSalary = Double.parseDouble(sal);
        double dInvestments = Double.parseDouble(inv);
        double dAllowance = Double.parseDouble(all);
        double dMiscellaneous = Double.parseDouble(misc);

        return dSalary + dInvestments + dAllowance + dMiscellaneous;
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