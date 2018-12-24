package com.cybersec.cybersec_project1.repository;

import com.cybersec.cybersec_project1.domain.Post;
import java.util.List;

public interface PostDAO {
   void save(Post post);
   List<Post> findAll();
   List<Post> search(String searchTerm);
   void delete(Long id);
}
