/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Account;
import com.cybersec.cybersec_project1.domain.Post;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jarno
 */
@Repository
public class PostDAOImpl implements PostDAO {
    @Autowired
    DataSource dataSource;

    @Autowired
    AccountDAOImpl accountDao;
        
    public void save(Post post) {
        Statement stmt = null;
        try {
           String sql = "INSERT INTO posts (title, content, account_id) VALUES ('" 
                   + post.getTitle() 
                   + "', '" 
                   + post.getContent() 
                   + "', "
                   + post.getUser().getId()
                   + ");";
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

    public List<Post> findAll() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<Post>();
        try {
           String sql = "SELECT * FROM posts";
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Long id = rs.getLong("id");
                    Account acc = accountDao.findById(rs.getLong("account_id"));
                    posts.add(new Post(id, title, content, acc));
                }
                rs.close();
                stmt.close();
                conn.close();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    public List<Post> search(String searchTerm) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<Post>();
        try {
            String sql = "SELECT * FROM posts WHERE "
                    + "title LIKE '%" + searchTerm + "%' OR "
                    + "content LIKE '%" + searchTerm + "%';";   
            Connection conn = dataSource.getConnection();
                        
            stmt = conn.createStatement();
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    Long id = rs.getLong(1);
                    String title = rs.getString(2);
                    String content = rs.getString(3);
                    Account acc = accountDao.findById(rs.getLong(4));
                    posts.add(new Post(id, title, content, acc));
                }
                rs.close();
                stmt.close();
                conn.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;               
    }
    public void delete(Long id) {
        Statement stmt = null;        
        try {
            String sql = "DELETE FROM posts WHERE id=" + id;
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
