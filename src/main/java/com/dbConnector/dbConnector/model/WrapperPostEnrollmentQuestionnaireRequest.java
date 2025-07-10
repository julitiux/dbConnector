package com.dbConnector.dbConnector.model;

import lombok.Data;

import java.util.List;

@Data
public class WrapperPostEnrollmentQuestionnaireRequest {

  private List<AnswersDetails> questions;

}
