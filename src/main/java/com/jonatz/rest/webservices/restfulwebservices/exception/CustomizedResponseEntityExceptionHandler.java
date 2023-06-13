package com.jonatz.rest.webservices.restfulwebservices.exception;

import com.jonatz.rest.webservices.restfulwebservices.user.UserNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request)
      throws Exception {
    ErrorDetails errorDetails =
        new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleUserNotFoundException(
      Exception ex, WebRequest request) throws Exception {
    ErrorDetails errorDetails =
        new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    String totalErrors = String.format("Total errors [%s]:", ex.getErrorCount());
    List<String> errorMessages = getErrorMessages(ex);
    String errorMessage = String.format("%s %s", totalErrors, String.join(", ", errorMessages));

    ErrorDetails errorDetails =
        new ErrorDetails(LocalDateTime.now(), errorMessage, request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  private List<String> getErrorMessages(MethodArgumentNotValidException ex) {
    return IntStream.range(0, ex.getFieldErrors().size())
        .mapToObj(
            i -> String.format("(%d) %s", i + 1, ex.getFieldErrors().get(i).getDefaultMessage()))
        .collect(Collectors.toList());
  }
}
