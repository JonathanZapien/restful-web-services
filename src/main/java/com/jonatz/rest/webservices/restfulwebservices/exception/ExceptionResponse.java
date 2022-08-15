package com.jonatz.rest.webservices.restfulwebservices.exception;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

  private Date timestamp;
  private String message;
  private String details;
}
