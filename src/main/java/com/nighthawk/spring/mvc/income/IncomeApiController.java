package com.nighthawk.spring.mvc.income;

import java.util.List;
// import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nighthawk.spring.mvc.user.User;

// import com.nighthawk.spring.mvc.expenses.Expenses;

@RestController
@RequestMapping("/dashboard/income")
public class IncomeApiController {

    @Autowired
    private IncomeJpaRepository incomeRepository;

    @GetMapping("/")
    public ResponseEntity<List<Income>> getIncome() {
        return new ResponseEntity<>(incomeRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }

    // @PostMapping("/updateAll") // ability to update one or all depending on how many boxes are filled
    // public ResponseEntity<Object> updateIncome(@RequestBody final Map<String, Double> m) {
    //     double salary = m.get("salary");
    //     double investments = m.get("investments");
    //     double allowance = m.get("allowance");
    //     double miscellaneous = m.get("miscellaneous");

    //     Income income = incomeRepository.findBySalary(salary);

    //     if (income != null) {
    //         income.setSalary(salary);
    //         income.setInvestments(investments);
    //         income.setAllowance(allowance);
    //         income.setMiscellaneous(miscellaneous);
            
    //         Income total = income.calculateIncome(salary, investments, allowance, miscellaneous);
    //         incomeRepository.save(income);
    //         incomeRepository.save(total);

    //         return new ResponseEntity<>(total + " updated", HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
    // }

    // @PostMapping("/post/") //new page for posting - rather than pop out window on mai dashboard
    // public ResponseEntity<Object> postIncome (@RequestBody final Map<String, Double> m) { // <List<Expenses>>
    //     double salary = m.get("salary");
    //     double investments = m.get("investments");
    //     double allowance = m.get("allowance");
    //     double miscellaneous = m.get("miscellaneous");
    //     Income post = new Income(salary, investments, allowance, miscellaneous);
    //     Income total = post.calculateIncome(salary, investments, allowance, miscellaneous);

    //     incomeRepository.save(post);
    //     incomeRepository.save(total);
    //     return new ResponseEntity<>(total + " listed successfully!", HttpStatus.CREATED);
    // }
    @PostMapping("/create/{salary}/{investments}/{allowance}/{miscellaneous}")
    public ResponseEntity<Income> createIncome(@PathVariable(required=false) String salary, @PathVariable(required=false) String investments,
    @PathVariable(required=false) String allowance, @PathVariable(required=false) String miscellaneous) {
        double dSalary = Double.parseDouble(salary);
        double dInvestments = Double.parseDouble(investments);
        double dAllowance = Double.parseDouble(allowance);
        double dMiscellaneous = Double.parseDouble(miscellaneous);
        User user = new User();
        
        Income i = new Income(user, dSalary, dInvestments, dAllowance, dMiscellaneous);
        // IncomeRepository.saveAndFlush(new Income(shopping, eatingOut, travel, miscellaneous));
        incomeRepository.save(i);
        double incomeTotal = i.calculateIncome();
        // incomeRepository.saveAndFlush(total);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable long id, @RequestBody Income changes) {
        Optional<Income> optional = incomeRepository.findById(id);
        if (optional.isPresent()) { // Good ID
            Income a = optional.get(); // value from findByID
            a.setSalary(changes.getSalary()); // value from findByID
            a.setInvestments(changes.getInvestments()); // value from findByID
            a.setAllowance(changes.getAllowance()); // value from findByID
            a.setMiscellaneous(changes.getMiscellaneous()); // value from findByID
            incomeRepository.save(a);
            double incomeTotal = a.calculateIncome();
            // incomeRepository.save(total);
            return new ResponseEntity<>(a, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
