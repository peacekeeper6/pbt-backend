package com.nighthawk.spring.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
This class has an instance of Java Persistence API (JPA)
-- @Autowired annotation. Allows Spring to resolve and inject collaborating beans into our bean.
-- Spring Data JPA will generate a proxy instance
-- Below are some CRUD methods that we can use with our database
*/
@Service
@Transactional
public class UserDetailService implements UserDetailsService {  // "implements" ties ModelRepo to Spring Security
    // Encapsulate many object into a single Bean (User, Roles, and Scrum)
    @Autowired  // Inject UserJpaRepository
    private UserJpaRepository userJpaRepository;
    // @Autowired  // Inject PasswordEncoder
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* UserDetailsService Overrides and maps User & Roles POJO into Spring Security */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userJpaRepository.findByEmail(email); // setting variable user equal to the method finding the username in the database
        if(user==null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER")); //create a SimpleGrantedAuthority by passed in role, adding it all to the authorities list, list of roles gets past in for spring security
        // train spring security to User and Authorities
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    /* User Section */

    public  List<User>listAll() {
        return userJpaRepository.findAllByOrderByEmailAsc();
    }

    // custom query to find match to name or email
    public  List<User>list(String email) {
        return userJpaRepository.findByEmailContainingIgnoreCase(email);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<User>listLike(String term) {
        return userJpaRepository.findByEmailContainingIgnoreCase(term);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<User>listLikeNative(String term) {
        String like_term = String.format("%%%s%%",term);  // Like required % rappers
        return userJpaRepository.findByLikeTermNative(like_term);
    }

    // encode password prior to sava
    public void save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userJpaRepository.save(user);
    }

    public User get(long id) {
        return (userJpaRepository.findById(id).isPresent())
                ? userJpaRepository.findById(id).get()
                : null;
    }

    public User getByEmail(String email) {
        return (userJpaRepository.findByEmail(email));
    }

    public void delete(long id) {
        userJpaRepository.deleteById(id);
    }

    public void defaults(String password) {
        for (User user: listAll()) {
            if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                user.setPassword(passwordEncoder().encode(password));
            }
        }
    }
}