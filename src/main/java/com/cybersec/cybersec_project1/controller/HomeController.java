/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import com.cybersec.cybersec_project1.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cybersec.cybersec_project1.repository.AccountRepository;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Jarno
 */
@Controller
public class HomeController {
  
   @RequestMapping("/")
   public String getForumPage() {
       return "redirect:/frontPage";
   }
}
