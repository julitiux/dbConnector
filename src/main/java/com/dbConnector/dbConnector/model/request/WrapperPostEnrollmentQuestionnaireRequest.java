package com.dbConnector.dbConnector.model.request;

import lombok.Data;

import java.util.List;

@Data
public class WrapperPostEnrollmentQuestionnaireRequest {

  private List<AnswersDetails> questions;

}
