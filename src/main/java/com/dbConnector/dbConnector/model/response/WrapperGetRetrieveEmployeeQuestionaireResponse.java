package com.dbConnector.dbConnector.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WrapperGetRetrieveEmployeeQuestionaireResponse {

  private List<Questionnaire> questions;

}
