/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import com.cybersec.cybersec_project1.repository.AccountRepository;
import com.cybersec.cybersec_project1.repository.PostRepository;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontPageController {
    @Autowired
    private PostRepository postRepository;
   
    @Autowired
    private AccountRepository accountRepository;
   
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    @RequestMapping("/frontPage")
    public String getFrontPage(Model model, Principal principal) {
        if (principal != null) model.addAttribute("user", accountRepository.findByUsername(principal.getName()));
        model.addAttribute("posts", postRepository.findAll());
        return "forum";
    }
}
