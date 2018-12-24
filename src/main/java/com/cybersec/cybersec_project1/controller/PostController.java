/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import com.cybersec.cybersec_project1.domain.Account;
import com.cybersec.cybersec_project1.domain.Post;
import com.cybersec.cybersec_project1.repository.AccountRepository;
import com.cybersec.cybersec_project1.repository.PostRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jarno
 */
@Controller
public class PostController {
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    PostRepository postRepository;
    
    @RequestMapping(value="/posts", method = RequestMethod.POST) 
    public String newPost(@RequestParam("title") String title,
            @RequestParam("content") String content,
            Principal principal) {
        Account user = accountRepository.findByUsername(principal.getName());
        Post post = new Post(title, content, user);
        postRepository.save(post);
        return "redirect:/";
    }
}
