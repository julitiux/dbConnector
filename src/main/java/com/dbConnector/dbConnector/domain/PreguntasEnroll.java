package com.dbConnector.dbConnector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "preguntas_enroll")
public class PreguntasEnroll {

  @EmbeddedId
  private PreguntasEnrollId id;

  @Column(name = "respuesta_pregunta", length = 20)
  private String respuestaPregunta;

  @Column(name = "estatus", length = 20)
  private String estatus;

  @Column(name = "fecha_hora", length = 20)
  private String fechaHora;
}
