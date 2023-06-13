package com.jonatz.rest.webservices.restfulwebservices.user;

import com.jonatz.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.jonatz.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaResource {

  private UserRepository userRepository;
  private PostRepository postRepository;

  protected UserJpaResource() {}

  @Autowired
  public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/jpa/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    User user = getUserById(id);
    EntityModel<User> entityModel = EntityModel.of(user);
    WebMvcLinkBuilder link =
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all-users"));
    return entityModel;
  }

  @PostMapping("/jpa/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    User savedUser = saveUser(user);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping("/jpa/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteById(id);
  }

  @GetMapping("/jpa/users/{id}/posts")
  public List<Post> retrievePostsForUser(@PathVariable int id) {
    return userRepository.findPostsById(id);
  }

  @PostMapping("/jpa/users/{id}/posts")
  public ResponseEntity<Object> createPostForUser(
      @PathVariable int id, @Valid @RequestBody Post post) {
    User user = getUserById(id);
    post.setUser(user);
    Post savedPost = savePost(post);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedPost.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  private User getUserById(int id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException("id: " + id);
    }
    return user.get();
  }

  private User saveUser(User user) {
    return userRepository.save(user);
  }

  private Post savePost(Post post) {
    return postRepository.save(post);
  }
}
