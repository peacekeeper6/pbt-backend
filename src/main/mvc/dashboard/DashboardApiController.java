package com.nighthawk.spring_portfolio.mvc.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import com.nighthawk.spring_portfolio.mvc.calendar.Calendar;

@RestController
@RequestMapping("/dashboard")
public class DashboardApiController {

    @Autowired
    private DashboardJpaRepository dashboardRepository;

    @GetMapping("/")
    public ResponseEntity<List<Dashboard>> getDashboard() {
        return new ResponseEntity<>(dashboardRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }

    @GetMapping("/") 
    public ResponseEntity<List<Dashboard>> getIncome() {
        return new ResponseEntity<>(dashboardRepository.findByOrderByIncomeNumberAsc(), HttpStatus.OK);
    }

    @GetMapping("/") 
    public ResponseEntity<List<Dashboard>> getExpenses() {
        return new ResponseEntity<>(dashboardRepository.findByOrderByExpensesNumberAsc(), HttpStatus.OK);
    }

    @GetMapping("/") 
    public ResponseEntity<List<Dashboard>> getBudgeting() {
        return new ResponseEntity<>(dashboardRepository.findByOrderByBudgetingNumberAsc(), HttpStatus.OK);
    }

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
