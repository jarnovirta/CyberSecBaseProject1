/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cybersec.cybersec_project1.repository.AccountDAO;
import com.cybersec.cybersec_project1.repository.PostDAO;

@Controller
public class FrontPageController {
    @Autowired
    private PostDAO postRepository;
   
    @Autowired
    private AccountDAO accountRepository;
   
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    
    @RequestMapping("/frontPage")
    public String getFrontPage(Model model, Principal principal) {
        if (principal != null) model.addAttribute("user", accountRepository.findByUsername(principal.getName()));
        model.addAttribute("posts", postRepository.findAll());
        return "forum";
    }
}
