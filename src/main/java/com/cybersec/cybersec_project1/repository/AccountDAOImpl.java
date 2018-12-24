/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Jarno
 */
@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    DataSource dataSource;    
    
    public Account findById(Long id) {
        Statement stmt = null;
        ResultSet rs = null;
        Account acc = null;
        try {
           String sql = "SELECT * FROM accounts WHERE id=" + id;
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                if (rs.next()) {
                    acc = new Account();
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setId(rs.getLong("id"));
                    
                }
                rs.close();
                stmt.close();
                conn.close();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return acc;        
    }
    public Account findByUsername(String username) {
        Statement stmt = null;
        ResultSet rs = null;
        Account acc = null;
        try {
           String sql = "SELECT * FROM accounts WHERE username='" + username + "'";
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                if (rs.next()) {
                    acc = new Account();
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setId(rs.getLong("id"));
                    
                }
                rs.close();
                stmt.close();
                conn.close();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return acc;
    }
    public void save(Account account) {
        Statement stmt = null;
        try {
           String sql = "INSERT INTO accounts (username, id) VALUES ('" 
                   + account.getUsername() + "', '" + account.getPassword() + "');";
            Connection conn = dataSource.getConnection();
            
            stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
            conn.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }    
    }
}
