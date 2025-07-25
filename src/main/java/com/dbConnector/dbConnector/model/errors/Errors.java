package com.dbConnector.dbConnector.model.errors;

import lombok.Data;

import java.util.List;

@Data
public class Errors {
  private List<Error> errors;
}
