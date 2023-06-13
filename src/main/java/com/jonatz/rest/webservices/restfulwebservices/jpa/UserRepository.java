package com.jonatz.rest.webservices.restfulwebservices.jpa;

import com.jonatz.rest.webservices.restfulwebservices.user.Post;
import com.jonatz.rest.webservices.restfulwebservices.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("SELECT p FROM Post p WHERE p.user.id = :id")
  List<Post> findPostsById(Integer id);
}
