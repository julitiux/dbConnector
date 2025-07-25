package com.dbConnector.dbConnector.model.response;

import lombok.Data;

import java.util.List;

@Data
public class WrapperGetRetrieveEmployeeQuestionsResponse {

  private List<WrapperQuestions> questions;

}
