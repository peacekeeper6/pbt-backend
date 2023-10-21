package com.nighthawk.spring.mvc.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import com.nighthawk.spring.mvc.budgeting.BudgetingJpaRepository;
// import com.nighthawk.spring.mvc.income.IncomeJpaRepository;
// import com.nighthawk.spring.mvc.expenses.ExpensesJpaRepository;
import com.nighthawk.spring.mvc.expenses.*;
import com.nighthawk.spring.mvc.budgeting.*;
import com.nighthawk.spring.mvc.income.*;
import com.nighthawk.spring.mvc.user.UserJpaRepository;


@RestController
@RequestMapping("/dashboard")
public class DashboardApiController {

    @Autowired
    private UserJpaRepository repository;

    @Autowired
    private DashboardJpaRepository dashboardRepository;

    @Autowired
    private BudgetingJpaRepository budgetingRepository;

    @Autowired
    private IncomeJpaRepository incomeRepository;

    @Autowired
    private ExpensesJpaRepository expensesRepository;

    @GetMapping("/{userId}")
        public ResponseEntity<Dashboard> getDashboard(@PathVariable("userId") Long userId, 
        @PathVariable("budgeting") Budgeting budgeting, @PathVariable("income") Income income, 
        @PathVariable("expenses") Expenses expenses) {
        Dashboard dashboard = dashboardRepository.findById(userId)
            .orElseThrow(() -> new NullPointerException("Cannot find User with id = " + userId));
        Budgeting findBudgeting = budgetingRepository.findById(userId); //we want to get the budgeting object input by userId
        Income findIncome = incomeRepository.findById(userId);
        Expenses findExpenses = expensesRepository.findById(userId);
        // Expenses e = repository.findById(userId).map(user -> {
        //     expenses.setUser(user);
        //     // income.setTotal(income.calculateIncome());
        //     expensesRepository.save(expenses);
        // }).orElseThrow(() -> new NullPointerException("No Expenses object with userId = " + userId));
        dashboard.setBudgeting(findBudgeting);
        dashboard.setIncome(findIncome);
        dashboard.setExpenses(findExpenses);
        return new ResponseEntity<>(dashboard, HttpStatus.OK);
            }

    // @GetMapping("/") 
    // public ResponseEntity<List<Dashboard>> getIncome() {
    //     return new ResponseEntity<>(dashboardRepository.findByOrderByIncomeDesc(), HttpStatus.OK);
    // }

    // @GetMapping("/") 
    // public ResponseEntity<List<Dashboard>> getExpenses() {
    //     return new ResponseEntity<>(dashboardRepository.findByOrderByExpensesDesc(), HttpStatus.OK);
    // }

    // @GetMapping("/") 
    // public ResponseEntity<List<Dashboard>> getBudgeting() {
    //     return new ResponseEntity<>(dashboardRepository.findByOrderByBudgetingDesc(), HttpStatus.OK);
    // }

    // post mapping for income, outcome, budgeting totals



    // @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Object> newDashboard(@RequestBody final Map<String, Double> m) {
    //     double income = (Double) m.get("income");
    //     double expenses = (Double) m.get("expenses");
    //     double budgeting = (Double) m.get("budgeting");
    //     // Date date;
    //     // try {
    //     //     date = new SimpleDateFormat("MM-dd-yyyy").parse(dateOfEvent);
    //     // } catch (Exception e) {
    //     //     return new ResponseEntity<>(dateOfEvent + " error; try MM-dd-yyyy",
    //     //             HttpStatus.BAD_REQUEST);
    //     // }
    //     Calendar calendar2 = new Calendar(event, note, date);
    //     calendarJpaRepository.save(calendar2);
    //     return new ResponseEntity<>(event + " created successfully!", HttpStatus.CREATED);
    // }
}
