/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Post;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jarno
 */
@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository {
    @Autowired
    private PostRepository jpaRepo;
    
    @Autowired
    EntityManager em;
    
    public Post save(Post post) {
        return jpaRepo.save(post);
    }
    public List<Post> findAll() {
        return jpaRepo.findAll();
    }
    public List<Post> search(String searchTerm) {
        String query = "SELECT p FROM Post p WHERE p.content LIKE '%" 
                + searchTerm + "%'";
        em.createQuery(query, Post.class).executeUpdate();
        return null;
        
    }
}
