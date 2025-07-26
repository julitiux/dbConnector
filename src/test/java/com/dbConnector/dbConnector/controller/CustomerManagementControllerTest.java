package com.dbConnector.dbConnector.controller;

import com.dbConnector.dbConnector.model.request.WrapperPostEnrollQuestionsRequest;
import com.dbConnector.dbConnector.model.response.*;
import com.dbConnector.dbConnector.service.CatalogoPreguntasService;
import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import com.dbConnector.dbConnector.service.SupervisorExpedienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    final var employee_id = "ANY_ID";
    final var wrapperRequest = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.validatePreguntasEnroll(wrapperRequest, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.processValidatePreguntasEnroll(employee_id, wrapperRequest);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void testProcessValidatePreguntasEnrollWhenReturnNotFound() {
    final var employee_id = "ANY_ID";
    final var wrapperRequest = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.validatePreguntasEnroll(wrapperRequest, employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.processValidatePreguntasEnroll(employee_id, wrapperRequest);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void createPreguntasEnrollWhenReturnCreated() {
    final var employee_id = "ANY_ID";
    final var wrapperRequest = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.createPreguntasEnroll(wrapperRequest, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, wrapperRequest);
    assertEquals(201, response.getStatusCode().value(), "should be return HTTP 201");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void createPreguntasEnrollWhenReturnBadRequest() {
    final var employee_id = "ANY_ID";
    final var wrapperRequest = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.createPreguntasEnroll(wrapperRequest, employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, wrapperRequest);
    assertEquals(400, response.getStatusCode().value(), "should be return HTTP 400");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void getCatalogoPreguntasByEstatusWhenReturnOkAndTheResponseIsNotEmpty() {
    final var estatusPregunta = "A";
    final var questions = mock(WrapperQuestions.class);
    lenient().when(questions.getQuestionId()).thenReturn("1");
    lenient().when(questions.getDescription()).thenReturn("First question:");
    final var wrapperResponse = mock(WrapperGetRetrieveQuestionsResponse.class);
    lenient().when(wrapperResponse.getQuestions()).thenReturn(List.of(questions));
    when(catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta)).thenReturn(wrapperResponse);

    ResponseEntity<WrapperGetRetrieveQuestionsResponse> response =
      customerManagementController.getCatalogoPreguntasByEstatus(estatusPregunta);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertTrue(response.hasBody(), "has body");
  }

  @Test
  void getCatalogoPreguntasByEstatusWhenReturnOkAndTheResponseIsEmpty() {
    final var estatusPregunta = "A";
    final var wrapperResponse = mock(WrapperGetRetrieveQuestionsResponse.class);
    when(catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta)).thenReturn(wrapperResponse);

    ResponseEntity<WrapperGetRetrieveQuestionsResponse> response =
      customerManagementController.getCatalogoPreguntasByEstatus(estatusPregunta);
    assertEquals(404, response.getStatusCode().value(), "should be return HTTP 404");
    assertFalse(response.hasBody(), "has not body");
  }

  @Test
  void getSubordinadosByExpedienteWhenReturnIsOk() {
    final var employee_id = "ANY_ID";
    final var wrapperSubordinates = mock(WrapperSubordinates.class);
    lenient().when(wrapperSubordinates.getNumber()).thenReturn("1");
    final var wrapperResponse = mock(WrapperGetRetrieveSubordinatesResponse.class);
    lenient().when(wrapperResponse.getSubordinates()).thenReturn(List.of(wrapperSubordinates));
    when(supervisorExpedienteService.getSubordinariosId(employee_id)).thenReturn(wrapperResponse);

    ResponseEntity<WrapperGetRetrieveSubordinatesResponse> response = customerManagementController.getSubordinadosByExpediente(employee_id);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertTrue(response.hasBody(), "has body");
  }

  @Test
  void getSubordinadosByExpedienteWhenReturnIsNotContent() {
    final var employee_id = "ANY_ID";
    final var wrapperResponse = mock(WrapperGetRetrieveSubordinatesResponse.class);
    when(supervisorExpedienteService.getSubordinariosId(employee_id)).thenReturn(wrapperResponse);

    ResponseEntity<WrapperGetRetrieveSubordinatesResponse> response = customerManagementController.getSubordinadosByExpediente(employee_id);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "has not body");
  }

  @Test
  void getPreguntasEnrollByExpedienteWhenReturnIsOk() {
    final var employee_id = "ANY_ID";
    final var questions = mock(WrapperQuestions.class);
    lenient().when(questions.getQuestionId()).thenReturn("1");
    final var wrapperResponse = mock(WrapperGetRetrieveEmployeeQuestionsResponse.class);
    lenient().when(wrapperResponse.getQuestions()).thenReturn(List.of(questions));
    lenient().when(preguntasEnrollService.getPreguntasEnrollByExpediente(employee_id)).thenReturn(wrapperResponse);

    ResponseEntity<WrapperGetRetrieveEmployeeQuestionsResponse> response = customerManagementController.getPreguntasEnrollByExpediente(employee_id);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertTrue(response.hasBody(), "has body");
  }

  @Test
  void getPreguntasEnrollByExpedienteWhenReturnIsNoContent() {
    final var employee_id = "ANY_ID";
    final var wrapperResponse = mock(WrapperGetRetrieveEmployeeQuestionsResponse.class);
    when(preguntasEnrollService.getPreguntasEnrollByExpediente(employee_id)).thenReturn(wrapperResponse);

    ResponseEntity<WrapperGetRetrieveEmployeeQuestionsResponse> response = customerManagementController.getPreguntasEnrollByExpediente(employee_id);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "has body");
  }

  @Test
  void deletePreguntasEnrollByExpedienteWhenReturnNoContent() {
    final var employee_id = "ANY_ID";
    when(preguntasEnrollService.deletePreguntasEnrollByExpediente(employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.deletePreguntasEnrollByExpediente(employee_id);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "no has body");
  }

  @Test
  void deletePreguntasEnrollByExpedienteWhenReturnNotFound() {
    final var employee_id = "ANY_ID";
    when(preguntasEnrollService.deletePreguntasEnrollByExpediente(employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.deletePreguntasEnrollByExpediente(employee_id);
    assertEquals(404, response.getStatusCode().value(), "should be return HTTP 404");
    assertFalse(response.hasBody(), "no has body");
  }
}