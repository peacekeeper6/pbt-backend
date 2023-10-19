package com.nighthawk.spring_portfolio.mvc.budgeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard/budgeting")
public class BudgetingApiController {

    @Autowired
    private BudgetingJpaRepository budgetingRepository;

    @GetMapping("/")
    public ResponseEntity<List<Budgeting>> getBudgeting() {
        return new ResponseEntity<>(budgetingRepository.findAll(), HttpStatus.OK);
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

    @PostMapping("/create/{shopping}/{eatingOut}/{subscriptions}/{travel}/{miscellaneous}")
    public ResponseEntity<Budgeting> createBudgeting(@PathVariable(required=false) String shopping, @PathVariable(required=false) String eating,
    @PathVariable(required=false) String subscriptions, @PathVariable(required=false) String travel, @PathVariable(required=false) String miscellaneous) {
        Budgeting b = new Budgeting(null, shopping, eating, travel, miscellaneous);
        // budgetingRepository.saveAndFlush(new Budgeting(shopping, eatingOut, travel, miscellaneous));
        budgetingRepository.saveAndFlush(b);
        total = b.calculateBudgeting(shopping, eating, travel, miscellaneous);
        budgetingRepository.saveAndFlush(total);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Budgeting> updateListing(@PathVariable long id, @RequestBody Budgeting changes) {
        Optional<Budgeting> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Budgeting a = optional.get(); // value from findByID
            a.setShopping(changes.getShopping()); // value from findByID
            a.setEating(changes.getEating()); // value from findByID
            a.setTravel(changes.getTravel()); // value from findByID
            a.setMiscellaneous(changes.getMiscellaneous()); // value from findByID
            repository.save(a);
            total = a.calculateBudgeting(shopping, eating, travel, miscellaneous);
            repository.save(total);
            return new ResponseEntity<>(a, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
