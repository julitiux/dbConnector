package com.dbConnector.dbConnector.handler;

import com.dbConnector.dbConnector.model.errors.Error;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class DataAccessResourceExceptionHandler {

  @ExceptionHandler(value = DataAccessException.class)
  public ResponseEntity<?> exeption(DataAccessException exception) {

    List<Error> errors = List.of(new Error("500", "Technical Error", "Error", "Database Connection"));

    return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
