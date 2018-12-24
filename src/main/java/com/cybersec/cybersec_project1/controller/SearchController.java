/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.cybersec.cybersec_project1.repository.PostDAO;

/**
 *
 * @author Jarno
 */
@Controller
public class SearchController {
    @Autowired
    private PostDAO postRepository;
        
    @RequestMapping("/search")
    public String search(Model model, @RequestParam("searchTerm") String searchTerm) {
        model.addAttribute("posts", postRepository.search(searchTerm));
        model.addAttribute("searchTerm", searchTerm);
        return "forum";
    }
}
