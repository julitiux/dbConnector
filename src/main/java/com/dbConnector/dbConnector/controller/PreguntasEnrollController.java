package com.dbConnector.dbConnector.controller;


import com.dbConnector.dbConnector.model.request.WrapperPostEnrollmentQuestionnaireRequest;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveEmployeeQuestionaireResponse;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveQuestionsResponse;
import com.dbConnector.dbConnector.model.response.WrapperRetrieveSubordinateResponse;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import com.dbConnector.dbConnector.service.SupervisorExpedienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PathVariable("employee_id") String expediente, @RequestBody WrapperPostEnrollmentQuestionnaireRequest request) {
    log.info("Processing preguntas enroll for expediente: {}", expediente);

    Boolean matching = preguntasEnrollService.validatePreguntasEnroll(request, expediente);
    return matching ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @PostMapping("/{employee_id}/enrollment_questionnaire")
  public ResponseEntity<Void> createPreguntasEnroll(
    @PathVariable("employee_id") String expediente, @RequestBody WrapperPostEnrollmentQuestionnaireRequest request) {
    log.info("Creating preguntas enroll for expediente: {}", expediente);

    Boolean preguntasEnrollsCreated = preguntasEnrollService.createPreguntasEnroll(request, expediente);
    return preguntasEnrollsCreated ?
      ResponseEntity.status(HttpStatus.CREATED).build() :
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping("/{status_id}/questions")
  public ResponseEntity<WrapperGetRetrieveQuestionsResponse> getCatalogoPreguntasByEstatus(
    @PathVariable("status_id") String estatusPregunta) {
    log.info("Fetching catalogo preguntas by estatus: {}", estatusPregunta);

    return ResponseEntity.ok(catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta));
  }

  @GetMapping("/{employee_id}/retrieve_subordinates")
  public ResponseEntity<WrapperRetrieveSubordinateResponse> getSubordinadosByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Fetching subordinados by expediente: {}", expediente);

    WrapperRetrieveSubordinateResponse response = supervisorExpedienteService.getSubordinariosId(expediente);
    if (response.getSubordinates() != null && !response.getSubordinates().isEmpty()) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @GetMapping("/{employee_id}/questionnaire")
  public ResponseEntity<WrapperGetRetrieveEmployeeQuestionaireResponse> getPreguntasEnrollByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Fetching preguntas enroll by expediente: {}", expediente);

    WrapperGetRetrieveEmployeeQuestionaireResponse response = preguntasEnrollService.getPreguntasEnrollByExpediente(expediente);
    if (response.getQuestions() != null && !response.getQuestions().isEmpty()) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @DeleteMapping("/{employee_id}/delete")
  public ResponseEntity<Void> deletePreguntasEnrollByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Deleting preguntas enroll for expediente: {}", expediente);

    Boolean expedienteDeleted = preguntasEnrollService.deletePreguntasEnrollByExpediente(expediente);
    return expedienteDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
