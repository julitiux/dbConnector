package com.dbConnector.dbConnector.controller;


import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

  @DeleteMapping("/{employee_id}/delete")
  public ResponseEntity<Void> deletePreguntasEnrollByExpediente(@PathVariable("employee_id") String expediente) {
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

  @PostMapping("/{employee_id}/enrollment_questionnaire")
  public ResponseEntity<List<PreguntasEnroll>> createPreguntasEnroll(
    @PathVariable("employee_id") String expediente,
    @RequestBody List<PreguntasEnroll> preguntasEnrolls) {
    log.info("Creating preguntas enroll for expediente: {}", expediente);

    List<PreguntasEnroll> createdPreguntasEnrolls =
      preguntasEnrollService.createPreguntasEnroll(preguntasEnrolls, expediente);
    return ResponseEntity.ok(createdPreguntasEnrolls);
  }

  @GetMapping("/{employee_id}/questionnaire")
  public ResponseEntity<List<Integer>> getPreguntasEnrollByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Fetching preguntas enroll by expediente: {}", expediente);

    List<Integer> preguntasEnrolls = preguntasEnrollService.getPreguntasEnrollByExpediente(expediente);

    log.info("{}", preguntasEnrolls.size());
    return Optional.of(preguntasEnrolls)
      .filter(list -> !list.isEmpty())
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PostMapping("/{employee_id}/validate_questionnaire")
  public ResponseEntity<List<PreguntasEnroll>> processValidatePreguntasEnroll(
    @PathVariable("employee_id") String expediente,
    @RequestBody List<PreguntasEnroll> preguntasEnrolls) {
    log.info("Processing preguntas enroll for expediente: {}", expediente);

    List<PreguntasEnroll> matching = preguntasEnrollService.validatePreguntasEnroll(preguntasEnrolls, expediente);

    return Optional.of(matching)
      .filter(list -> !list.isEmpty())
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.noContent().build());
  }


}
