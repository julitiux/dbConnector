package com.dbConnector.dbConnector.controller;

import com.dbConnector.dbConnector.model.request.WrapperPostEnrollmentQuestionnaireRequest;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import com.dbConnector.dbConnector.service.SupervisorExpedienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerManagementControllerTest {

  @Mock
  private PreguntasEnrollService preguntasEnrollService;
  @Mock
  private CatalogoPreguntasService catalogoPreguntasService;
  @Mock
  private SupervisorExpedienteService supervisorExpedienteService;
  @InjectMocks
  CustomerManagementController customerManagementController;

  @Test
  void testProcessValidatePreguntasEnrollWhenReturnOk() {

    final String employee_id = "ANY_ID";
    final WrapperPostEnrollmentQuestionnaireRequest request = new WrapperPostEnrollmentQuestionnaireRequest();
    when(preguntasEnrollService.validatePreguntasEnroll(request, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.processValidatePreguntasEnroll(employee_id, request);

    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void testProcessValidatePreguntasEnrollWhenReturnNotFound() {

    final String employee_id = "ANY_ID";
    final WrapperPostEnrollmentQuestionnaireRequest request = new WrapperPostEnrollmentQuestionnaireRequest();
    when(preguntasEnrollService.validatePreguntasEnroll(request, employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.processValidatePreguntasEnroll(employee_id, request);

    assertEquals(404, response.getStatusCode().value(), "should be return HTTP 404");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void createPreguntasEnroll() {

    final String employee_id = "ANY_ID";
    final WrapperPostEnrollmentQuestionnaireRequest request = new WrapperPostEnrollmentQuestionnaireRequest();

    when(preguntasEnrollService.createPreguntasEnroll(request, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, request);

    assertEquals(201, response.getStatusCode().value(), "should be return HTTP 201");
    assertFalse(response.hasBody(), "dont has body");

  }

  @Test
  void getCatalogoPreguntasByEstatus() {
  }

  @Test
  void getSubordinadosByExpediente() {
  }

  @Test
  void getPreguntasEnrollByExpediente() {
  }

  @Test
  void deletePreguntasEnrollByExpediente() {
  }
}