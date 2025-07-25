package com.dbConnector.dbConnector.controller;

import com.dbConnector.dbConnector.model.request.WrapperPostEnrollQuestionsRequest;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveEmployeeQuestionsResponse;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveQuestionsResponse;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveSubordinatesResponse;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import com.dbConnector.dbConnector.service.SupervisorExpedienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/customer_management")
public class CustomerManagementController {

  private final PreguntasEnrollService preguntasEnrollService;
  private final CatalogoPreguntasService catalogoPreguntasService;
  private final SupervisorExpedienteService supervisorExpedienteService;

  @PostMapping("/{employee_id}/questions/validate")
  public ResponseEntity<Void> processValidatePreguntasEnroll(
    @PathVariable("employee_id") String expediente, @RequestBody WrapperPostEnrollQuestionsRequest request) {
    log.info("Processing preguntas enroll for expediente: {}", expediente);

    Boolean matching = preguntasEnrollService.validatePreguntasEnroll(request, expediente);
    return matching ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
  }

  @PostMapping("/{employee_id}/enroll_questions")
  public ResponseEntity<Void> createPreguntasEnroll(
    @PathVariable("employee_id") String expediente, @RequestBody WrapperPostEnrollQuestionsRequest request) {
    log.info("Creating preguntas enroll for expediente: {}", expediente);

    Boolean preguntasEnrollsCreated = preguntasEnrollService.createPreguntasEnroll(request, expediente);
    return preguntasEnrollsCreated ?
      ResponseEntity.status(HttpStatus.CREATED).build() :
      ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping("/questions")
  public ResponseEntity<WrapperGetRetrieveQuestionsResponse> getCatalogoPreguntasByEstatus(
    @RequestParam("statusId") String estatusPregunta) {
    log.info("Fetching catalogo preguntas by estatus: {}", estatusPregunta);

    WrapperGetRetrieveQuestionsResponse response = catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta);
    if (response.getQuestions() != null && !response.getQuestions().isEmpty()) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{employee_id}/subordinates")
  public ResponseEntity<WrapperGetRetrieveSubordinatesResponse> getSubordinadosByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Fetching subordinados by expediente: {}", expediente);

    WrapperGetRetrieveSubordinatesResponse response = supervisorExpedienteService.getSubordinariosId(expediente);
    if (response.getSubordinates() != null && !response.getSubordinates().isEmpty()) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @GetMapping("/{employee_id}/questions")
  public ResponseEntity<WrapperGetRetrieveEmployeeQuestionsResponse> getPreguntasEnrollByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Fetching preguntas enroll by expediente: {}", expediente);

    WrapperGetRetrieveEmployeeQuestionsResponse response = preguntasEnrollService.getPreguntasEnrollByExpediente(expediente);
    if (response.getQuestions() != null && !response.getQuestions().isEmpty()) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  @DeleteMapping("/{employee_id}/questions")
  public ResponseEntity<Void> deletePreguntasEnrollByExpediente(@PathVariable("employee_id") String expediente) {
    log.info("Deleting preguntas enroll for expediente: {}", expediente);

    Boolean expedienteDeleted = preguntasEnrollService.deletePreguntasEnrollByExpediente(expediente);
    return expedienteDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
