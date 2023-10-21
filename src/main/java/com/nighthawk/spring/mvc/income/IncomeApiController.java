package com.nighthawk.spring.mvc.income;

import java.util.List;
// import java.util.Map;
import java.util.Optional;

// import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nighthawk.spring.mvc.user.User;
// import com.nighthawk.spring.mvc.exception.ResourceNotFoundException;
import com.nighthawk.spring.mvc.user.UserJpaRepository;

// import com.nighthawk.spring.mvc.expenses.Expenses;

@RestController
@RequestMapping("/dashboard/income")
public class IncomeApiController {

    @Autowired
    private UserJpaRepository repository;


    @Autowired
    private IncomeJpaRepository incomeRepository;

    // @GetMapping("/{userId}")
    // public ResponseEntity<List<Income>> getIncome(@PathVariable(value = "userId") Long userId) {
    //     if (!repository.existsById(userId)) {
    //         throw new NullPointerException("Cannot find User with id = " + userId); // placeholder exception, change later
    //       }
      
    //       List<Income> incomes = incomeRepository.findById(userId);
    //       return new ResponseEntity<>(incomes, HttpStatus.OK);
    //     // return new ResponseEntity<>(incomeRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncome(@PathVariable("id") Long userId) {
    Income income = incomeRepository.findById(userId)
        .orElseThrow(() -> new NullPointerException("Cannot find User with id = " + userId));

    return new ResponseEntity<>(income, HttpStatus.OK);
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
    @PostMapping("/create/{userId}")
    public ResponseEntity<Income> createIncome(@PathVariable(value = "userId") Long userId, @RequestBody Income income) {
        // Double dSalary = new Double(salary.toString());
        // Double dInvestments = new Double(investments.toString());
        // Double dAllowance = new Double(allowance.toString());
        // Double dMisellaneous = new Double(miscellaneous.toString());
        // Double.parseDouble(salary);
        // double dInvestments = Double.parseDouble(investments);
        // double dAllowance = Double.parseDouble(allowance);
        // double dMiscellaneous = Double.parseDouble(miscellaneous);
        // User user = new User();
        // Long id = user.get(); // intake user id to pair with income input
        // Income i = new Income(dSalary, dInvestments, dAllowance, dMiscellaneous);
        // // IncomeRepository.saveAndFlush(new Income(shopping, eatingOut, travel, miscellaneous));
        // incomeRepository.save(i);
        Income i = repository.findById(userId).map(user -> {
            income.setUser(user);
            // income.setTotal(income.calculateIncome());
            return incomeRepository.save(income);
        }).orElseThrow(() -> new NullPointerException("No Income object with userId = " + userId));
        // double incomeTotal = i.calculateIncome();
        // Income i = incomeRepository.findById(userId).map(user -> {
        //     commentRequest.setUser(user);
        //     return commentRepository.save(commentRequest);
        //   }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
      
        //   return new ResponseEntity<>(comment, HttpStatus.CREATED);
        // incomeRepository.saveAndFlush(total);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Income> updateIncome(@PathVariable(value = "userId") Long userId, @RequestBody Income income
        // @PathVariable long id, @RequestBody Income changes
        ) {
        Income changes = incomeRepository.findById(userId)
            .orElseThrow(() -> new NullPointerException("No Income object with userId = " + userId));
        // Optional<Income> optional = incomeRepository.findById(id);
        changes.setSalary(income.getSalary());
        changes.setInvestments(income.getInvestments());
        changes.setAllowance(income.getAllowance());
        changes.setMiscellaneous(income.getMiscellaneous());
        // if (optional.isPresent()) { // Good ID
        //     Income a = optional.get(); // value from findByID
        //     a.setSalary(changes.getSalary()); // value from findByID
        //     a.setInvestments(changes.getInvestments()); // value from findByID
        //     a.setAllowance(changes.getAllowance()); // value from findByID
        //     a.setMiscellaneous(changes.getMiscellaneous()); // value from findByID
        //     incomeRepository.save(a);
        //     double incomeTotal = a.calculateIncome();
            // incomeRepository.save(total);
            return new ResponseEntity<>(incomeRepository.save(changes), HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Income> deleteIncome(@PathVariable("userId") Long userId) {
    incomeRepository.deleteById(userId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    }
// }