package com.dbConnector.dbConnector.controller;


import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaire;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import com.dbConnector.dbConnector.service.SupervisorExpedienteService;
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
  private final SupervisorExpedienteService supervisorExpedienteService;

  public PreguntasEnrollController(PreguntasEnrollService preguntasEnrollService, CatalogoPreguntasService catalogoPreguntasService, SupervisorExpedienteService supervisorExpedienteService) {
    this.preguntasEnrollService = preguntasEnrollService;
    this.catalogoPreguntasService = catalogoPreguntasService;
    this.supervisorExpedienteService = supervisorExpedienteService;
  }

  @PostMapping("/{employee_id}/validate_questionnaire")
  public ResponseEntity<Void> processValidatePreguntasEnroll(
    @PathVariable("employee_id") String expediente,
    @RequestBody WrapperPostEnrollmentQuestionnaire request) {
    log.info("Processing preguntas enroll for expediente: {}", expediente);

    Boolean matching = preguntasEnrollService.validatePreguntasEnroll(request, expediente);
    return matching ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
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

  @GetMapping("/{employee_id}/retrieve_subordinates")
  public ResponseEntity<List<String>> getSubordinadosByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Fetching subordinados by expediente: {}", expediente);

    List<String> subordinados = supervisorExpedienteService.getSubordinariosId(expediente);

    return Optional.of(subordinados)
      .filter(list -> !list.isEmpty())
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.noContent().build());
  }
}
