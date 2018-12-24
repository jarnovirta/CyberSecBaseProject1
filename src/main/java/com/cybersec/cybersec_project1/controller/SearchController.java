/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.controller;

import com.cybersec.cybersec_project1.repository.CustomPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Jarno
 */
@Controller
public class SearchController {
    @Autowired
    private CustomPostRepository postRepository;
        
    @RequestMapping("/search")
    public String search(Model model, @RequestParam("searchTerm") String searchTerm) {
        model.addAttribute("posts", postRepository.search(searchTerm));
        return "forum";
    }
}
