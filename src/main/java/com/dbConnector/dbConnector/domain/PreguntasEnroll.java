package com.dbConnector.dbConnector.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_preguntas_enroll")
public class PreguntasEnroll {

  @EmbeddedId
  private PreguntasEnrollId id;

  @Column(name = "respuesta_pregunta", length = 20)
  private String respuestaPregunta;

  @Column(name = "estatus", length = 20)
  private String estatus;

  @Column(name = "fecha_hora", length = 20)
  private String fechaHora;

  @ManyToOne
  @JoinColumn(name = "no_pregunta", referencedColumnName = "no_pregunta", insertable = false, updatable = false)
  private CatalogoPreguntas catalogoPreguntas;
}
