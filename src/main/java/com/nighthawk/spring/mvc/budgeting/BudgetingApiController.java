package com.nighthawk.spring.mvc.budgeting;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring.mvc.expenses.Expenses;
import com.nighthawk.spring.mvc.income.Income;
import com.nighthawk.spring.mvc.user.User;
import com.nighthawk.spring.mvc.user.UserJpaRepository;

@RestController
@RequestMapping("/dashboard/budgeting")
public class BudgetingApiController {

    @Autowired
    private UserJpaRepository repository;
    
    @Autowired
    private BudgetingJpaRepository budgetingRepository;

    @GetMapping("/{userId}")
        public ResponseEntity<Budgeting> getBudgeting(@PathVariable("userId") Long userId) {
        Budgeting budgeting = budgetingRepository.findById(userId)
            .orElseThrow(() -> new NullPointerException("Cannot find User with id = " + userId));

        return new ResponseEntity<>(budgeting, HttpStatus.OK);
        }

     @PostMapping("/create/{userId}")
        public ResponseEntity<Budgeting> createBudgeting(@PathVariable(value = "userId") Long userId, @RequestBody Budgeting budgeting) {
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
            Budgeting b = repository.findById(userId).map(user -> {
                budgeting.setUser(user);
                // income.setTotal(income.calculateIncome());
                return budgetingRepository.save(budgeting);
            }).orElseThrow(() -> new NullPointerException("No Expenses object with userId = " + userId));
            // double incomeTotal = i.calculateIncome();
            // Income i = incomeRepository.findById(userId).map(user -> {
            //     commentRequest.setUser(user);
            //     return commentRepository.save(commentRequest);
            //   }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
          
            //   return new ResponseEntity<>(comment, HttpStatus.CREATED);
            // incomeRepository.saveAndFlush(total);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }
        
    @PutMapping("/update/{userId}")
    public ResponseEntity<Budgeting> updateBudgeting(@PathVariable(value = "userId") Long userId, @RequestBody Budgeting budgeting
        // @PathVariable long id, @RequestBody Income changes
        ) {
        Budgeting changes = budgetingRepository.findById(userId)
            .orElseThrow(() -> new NullPointerException("No Income object with userId = " + userId));
        // Optional<Income> optional = incomeRepository.findById(id);
        changes.setShopping(budgeting.getShopping());
        changes.setEating(budgeting.getEating());
        changes.setSubscriptions(budgeting.getSubscriptions());
        changes.setTravel(budgeting.getTravel());
        changes.setMiscellaneous(budgeting.getMiscellaneous());
        // if (optional.isPresent()) { // Good ID
        //     Income a = optional.get(); // value from findByID
        //     a.setSalary(changes.getSalary()); // value from findByID
        //     a.setInvestments(changes.getInvestments()); // value from findByID
        //     a.setAllowance(changes.getAllowance()); // value from findByID
        //     a.setMiscellaneous(changes.getMiscellaneous()); // value from findByID
        //     incomeRepository.save(a);
        //     double incomeTotal = a.calculateIncome();
            // incomeRepository.save(total);
            return new ResponseEntity<>(budgetingRepository.save(changes), HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Budgeting> deleteBudgeting(@PathVariable("userId") Long userId) {
    budgetingRepository.deleteById(userId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // @PostMapping("/updateAll") // ability to update one or all depending on how many boxes are filled
    // public ResponseEntity<Object> updateBudgeting (@RequestBody final Map<String, Double> m) {
    //     double salary = m.get("shopping");
    //     double investments = m.get("eatingOut");
    //     double allowance = m.get("subscriptions");
    //     double travel = m.get("travel");
    //     double miscellaneous = m.get("miscellaneous");

    //     Budgeting budget = budgetingRepository.findBySalary(salary);

    //     if (budget != null) {
    //         budget.setSalary(salary);
    //         budget.setInvestments(investments);
    //         budget.setAllowance(allowance);
    //         budget.setMiscellaneous(miscellaneous);
            
    //         Budgeting total = budget.calculateBudgeting(shopping, investments, allowance, travel, miscellaneous);
    //         budgetRepository.save(budget);
    //         budgetRepository.save(total);

    //         return new ResponseEntity<>(total + " updated", HttpStatus.OK);
    //     }
    //     return new ResponseEntity<>("Not found", HttpStatus.BAD_REQUEST);
    // }

    // @PostMapping("/post") //new page for posting - rather than pop out window on mai dashboard
    // public ResponseEntity<Object> postGroceries(@RequestBody final Map<String, Double> m) { // <List<Expenses>>
    //      double salary = m.get("shopping");
    //     double investments = m.get("eatingOut");
    //     double allowance = m.get("subscriptions");
    //     double travel = m.get("travel");
    //     double miscellaneous = m.get("miscellaneous");
    //     Budgeting post = new Budgeting(shopping, investments, allowance, miscellaneous);
    //     Budgeting total = post.calculateBudgeting(salary, investments, allowance, miscellaneous);

    //     budgetingRepository.save(post);
    //     budgetingRepository.save(total);
    //     return new ResponseEntity<>(total + " listed successfully!", HttpStatus.CREATED);
    // }

    // @GetMapping("/search/{name}") // don't need a search
    // public ResponseEntity<List<Budgeting>> searchBudgeting(@PathVariable String name) {
    //     List<Budgeting> listings = repository.findByNameIgnoreCase(name);
    //     return new ResponseEntity<>(listings,HttpStatus.OK);
    // }

    //create
    
    // @PostMapping("/create/{shopping}/{eating}/{subscriptions}/{travel}/{miscellaneous}")
    // public ResponseEntity<Budgeting> createBudgeting(
    //  @PathVariable String shopping, @PathVariable String eating,
    //  @PathVariable String subscriptions, @PathVariable String travel, @PathVariable String miscellaneous) 
    //                                                 { 
    //     double dShopping = Double.parseDouble(shopping);
    //     double dEating = Double.parseDouble(eating);
    //     double dSubscriptions = Double.parseDouble(subscriptions);
    //     double dTravel = Double.parseDouble(travel);
    //     double dMiscellaneous = Double.parseDouble(miscellaneous);
    //     User user = new User();

    //     Budgeting b = new Budgeting(user, dShopping, dEating, dSubscriptions, dTravel, dMiscellaneous);
    //     // budgetingRepository.saveAndFlush(new Budgeting(shopping, eatingOut, travel, miscellaneous));
    //     // System.out.println("went");
    //     budgetingRepository.save(b);
    //     // System.out.println("went in");
    //     double budgetingTotal = b.calculateBudgeting();
    //     // System.out.println("went through");
    //     // budgetingRepository.saveAndFlush(total);
    //     return new ResponseEntity<>(b, HttpStatus.OK);
    // }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<Budgeting> updateListing(@PathVariable long id, @RequestBody Budgeting changes) {
    //     Optional<Budgeting> optional = budgetingRepository.findById(id);
    //     if (optional.isPresent()) { // Good ID
    //         Budgeting a = optional.get(); // value from findByID
    //         a.setShopping(changes.getShopping()); // value from findByID
    //         a.setEating(changes.getEating()); // value from findByID
    //         a.setTravel(changes.getTravel()); // value from findByID
    //         a.setMiscellaneous(changes.getMiscellaneous()); // value from findByID
    //         budgetingRepository.save(a);
    //         double budgetingTotal = a.calculateBudgeting();
    //         // double total = a.calculateBudgeting(shopping, eating, subscriptions, travel, miscellaneous);
    //         // budgetingRepository.save(total);
    //         return new ResponseEntity<>(a, HttpStatus.OK); // OK HTTP response: status code, headers, and body
    //     }
    //     // Bad ID
    //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
}
