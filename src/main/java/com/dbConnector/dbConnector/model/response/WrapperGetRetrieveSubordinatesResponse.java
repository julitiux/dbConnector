package com.dbConnector.dbConnector.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WrapperGetRetrieveSubordinatesResponse {

  private List<WrapperSubordinates> subordinates;

}
