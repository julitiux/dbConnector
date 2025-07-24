package com.dbConnector.dbConnector.model.request;

import lombok.Data;

import java.util.List;

@Data
public class WrapperPostEnrollQuestionsRequest {

  private List<WrapperAnswer> questions;

}
