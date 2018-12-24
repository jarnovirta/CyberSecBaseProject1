package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PostRepository extends JpaRepository<Post, Long>  {
    
}
