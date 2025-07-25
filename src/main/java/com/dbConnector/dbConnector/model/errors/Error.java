package com.dbConnector.dbConnector.model.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
  private String code;
  private String description;
  private String level;
  private String message;
}
