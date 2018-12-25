/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.config;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jarno
 */
@Component
public class PopulateDb {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @PostConstruct
    protected void populateDb() {
        System.out.println("Creating and populating tables...");
        
        jdbcTemplate.execute("DROP TABLE accounts IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE accounts (" +
                "id SERIAL, username VARCHAR(50), password VARCHAR(80))");
        
        jdbcTemplate.execute("INSERT INTO accounts(username, password) VALUES"
                + " ('admin', '$2a$10$EcNri2HeQQkADevP4WPrd.Vfks1aMqzqT3v8e7r8RToUDSnHb5JQq')");
        
        // This user is provided for demo purposes for the Cyber Security Base
        // course:
        // username: 'kevinmitnick', password: '123456'
        jdbcTemplate.execute("INSERT INTO accounts(username, password) VALUES"
                + " ('kevinmitnick', '$2a$10$Ln6zFYd1n4eKAtk2pKo4nesRcle.xdp6D3D46mAbG/UzI.Bvmj1ii')");
        
        jdbcTemplate.execute("DROP TABLE posts IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE posts (" +
                "id SERIAL, title VARCHAR(100), content VARCHAR(1000), account_id BIGINT)");
        String title = "Welcome to the Forum!";
        String content = "Howdy Partner! Before you post in this very secure "
                + "forum remember to respect following rules and policies: blah blah blah blah...";
        String query = "INSERT INTO posts (title, content, account_id)"
                + " VALUES ('" + title + "', '" + content + "', 1);";
        
        jdbcTemplate.execute(query);
        
    }
}
