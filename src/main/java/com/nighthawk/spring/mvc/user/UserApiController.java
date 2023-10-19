package com.nighthawk.spring.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
// import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //     @Autowired
    // private JwtTokenUtil jwtGen;
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private UserJpaRepository repository;

    /*
    GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<User>> getPeople() {
        return new ResponseEntity<>( repository.findAllByOrderByEmailAsc(), HttpStatus.OK);
    }

    /*
    GET individual User using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            User user = optional.get();  // value from findByID
            return new ResponseEntity<>(user, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            User user = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(user, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    
    @PostMapping( "/post")
    public ResponseEntity<Object> postUser(@RequestParam("email") String email,
                                             @RequestParam("password") String password) {
        User user = new User(email, password);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        repository.save(user);
        return new ResponseEntity<>(email +" is created successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> userSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<User> list = repository.findByEmailContainingIgnoreCase(term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}