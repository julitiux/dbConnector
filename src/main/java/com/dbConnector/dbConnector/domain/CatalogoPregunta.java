package com.dbConnector.dbConnector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "catalogo_preguntas")
public class CatalogoPregunta {

  @Id
  @Column(name = "no_pregunta")
  private Integer noPregunta;

  @Column(name = "desc_pregunta", nullable = false, length = 20)
  private String descPregunta;

  @Column(name = "estatus_pregunta", nullable = false, length = 20)
  private String estatusPregunta;

  @Column(name = "fecha_inicio", nullable = false, length = 20)
  private String fechaInicio;

  @Column(name = "fecha_estatus", nullable = false, length = 20)
  private String fechaEstatus;
}
