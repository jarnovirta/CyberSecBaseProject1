/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.config;

import com.cybersec.cybersec_project1.domain.Account;
import com.cybersec.cybersec_project1.domain.Post;
import com.cybersec.cybersec_project1.repository.AccountRepository;
import com.cybersec.cybersec_project1.repository.PostRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jarno
 */
@Component
public class PopulateDb {
    @Autowired
    private AccountRepository accountRepo;
    
    @Autowired
    private PostRepository postRepo;
    
    @PostConstruct
    protected void populateDb() {
        System.out.println("Populating db...");
        Account admin = accountRepo.findByUsername("admin");
        Post post = new Post("Welcome to the Forum!", "Howdy Partner! "
                + "Before you post in this very secure forum remember to "
                + "respect following rules and policies: blah blah blah blah...",
                admin
            );
        postRepo.save(post);
    }
}
