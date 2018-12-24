/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import com.cybersec.cybersec_project1.domain.Account;
import com.cybersec.cybersec_project1.domain.Post;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.cybersec.cybersec_project1.repository.AccountDAO;
import com.cybersec.cybersec_project1.repository.PostDAO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Jarno
 */
@Controller
public class PostController {
    @Autowired
    AccountDAO accountRepository;
    
    @Autowired
    PostDAO postRepository;
    
    @RequestMapping(value="/posts", method = RequestMethod.POST) 
    public String newPost(@RequestParam("title") String title,
            @RequestParam("content") String content,
            Principal principal) {
        Account user = accountRepository.findByUsername(principal.getName());
        Post post = new Post(title, content, user);
        postRepository.save(post);
        return "redirect:/";
    }
    @RequestMapping(value="/posts/delete", method = RequestMethod.POST) 
    public String delete(@RequestParam("id") Long id) {
        System.out.println("got delete request");
        postRepository.delete(id);
        return "redirect:/";
    }
}
