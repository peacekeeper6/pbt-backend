package com.nighthawk.spring.mvc.user;

// import java.text.SimpleDateFormat;
// import java.time.LocalDate;
// import java.time.Period;
// import java.time.ZoneId;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.Convert;
// import static jakarta.persistence.FetchType.EAGER;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
// import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Convert(attributeName ="person", converter = JsonType.class)
public class User {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Initialize static test data 
    public static User[] init() {

        // basics of class construction
        User p1 = new User();
        p1.setEmail("toby@gmail.com");
        p1.setPassword("123Toby!");

        User p2 = new User();
        p2.setEmail("lexb@gmail.com");
        p2.setPassword("123LexB!");

        User p3 = new User();
        p3.setEmail("niko@gmail.com");
        p3.setPassword("123Niko!");

        User p4 = new User();
        p4.setEmail("madam@gmail.com");
        p4.setPassword("123Madam!");

        User p5 = new User();
        p5.setEmail("jm1021@gmail.com");
        p5.setPassword("123Qwerty!");

        // Array definition and data initialization
        User users[] = {p1, p2, p3, p4, p5};
        return(users);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        User users[] = init();

        // iterate using "enhanced for loop"
        for( User user : users) {
            System.out.println(user);  // print object
        }
    }

}