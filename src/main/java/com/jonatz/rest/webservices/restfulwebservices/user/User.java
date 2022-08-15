package com.jonatz.rest.webservices.restfulwebservices.user;

import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

  private Integer id;

  @Size(min = 2, message = "Name should have at least 2 characters.")
  private String name;

  @Past private Date birthDate;
}
