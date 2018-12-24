/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Post;
import java.util.List;

/**
 *
 * @author Jarno
 */
public interface CustomPostRepository {
    Post save(Post post);
    List<Post> findAll();
    List<Post> search(String searchTerm);
}
