package com.dbConnector.dbConnector.model.response;

import lombok.Data;

import java.util.List;

@Data
public class WrapperGetRetrieveSubordinatesResponse {

  private List<WrapperSubordinates> subordinates;

}
