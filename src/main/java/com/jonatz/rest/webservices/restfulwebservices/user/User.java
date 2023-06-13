package com.jonatz.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_details")
public class User {
  @Id @GeneratedValue private Integer id;

  @NotNull(message = "Name cannot be null")
  @Size(min = 2, message = "Name should have at least 2 characters")
  @JsonProperty("user_name")
  private String name;

  @NotNull(message = "Birth date cannot be null")
  @Past(message = "Birth date should be in the past")
  @JsonProperty("birth_date")
  private LocalDate birthDate;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Post> posts;
}
