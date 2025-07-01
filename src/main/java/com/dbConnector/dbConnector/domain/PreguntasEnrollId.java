package com.dbConnector.dbConnector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PreguntasEnrollId {

  @Column(name = "expediente", length = 20)
  private String expediente;
  @Column(name = "no_pregunta")
  private Integer noPregunta;

}
