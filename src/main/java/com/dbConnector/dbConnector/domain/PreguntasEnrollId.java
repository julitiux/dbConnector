package com.dbConnector.dbConnector.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PreguntasEnrollId implements Serializable {

  private String expediente;
  private Integer noPregunta;

}
