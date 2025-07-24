package com.dbConnector.dbConnector.model.domain;

import lombok.ToString;

@ToString
public class PreguntaDescripcionDTO {

  private Integer noPregunta;
  private String descPregunta;

  public PreguntaDescripcionDTO(Integer noPregunta, String descPregunta) {
    this.noPregunta = noPregunta;
    this.descPregunta = descPregunta;
  }

  public void setNoPregunta(Integer noPregunta) {
    this.noPregunta = noPregunta;
  }

  public void setDescPregunta(String descPregunta) {
    this.descPregunta = descPregunta;
  }

  public Integer getNoPregunta() {
    return noPregunta;
  }

  public String getDescPregunta() {
    return descPregunta;
  }
}
