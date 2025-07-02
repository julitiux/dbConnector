package com.dbConnector.dbConnector.controller;


import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/preguntas-enroll")
public class PreguntasEnrollController {

  private final PreguntasEnrollService preguntasEnrollService;
  private final CatalogoPreguntasService catalogoPreguntasService;

  public PreguntasEnrollController(PreguntasEnrollService preguntasEnrollService, CatalogoPreguntasService catalogoPreguntasService) {
    this.preguntasEnrollService = preguntasEnrollService;
    this.catalogoPreguntasService = catalogoPreguntasService;
  }

  @DeleteMapping("/{expediente}/delete")
  public ResponseEntity<Void> deletePreguntasEnrollByExpediente(@PathVariable("expediente") String expediente) {
    log.info("Deleting preguntas enroll for expediente: {}", expediente);

    Boolean expedienteDeleted = preguntasEnrollService.deletePreguntasEnrollByExpediente(expediente);
    return expedienteDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }

  @GetMapping("/{status_id}/questions")
  public ResponseEntity<List<CatalogoPreguntas>> getCatalogoPreguntasByEstatus(
    @PathVariable("status_id") String estatusPregunta) {
    log.info("Fetching catalogo preguntas by estatus: {}", estatusPregunta);

    List<CatalogoPreguntas> catalogoPreguntas = catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta);
    return ResponseEntity.ok(catalogoPreguntas);
  }
}
