package com.dbConnector.dbConnector.controller;

import com.dbConnector.dbConnector.model.request.WrapperPostEnrollQuestionsRequest;
import com.dbConnector.dbConnector.model.request.WrapperPostEnrollmentQuestionnaireRequest;
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
    final String employee_id = "ANY_ID";
    final WrapperPostEnrollQuestionsRequest request = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.validatePreguntasEnroll(request, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.processValidatePreguntasEnroll(employee_id, request);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void testProcessValidatePreguntasEnrollWhenReturnNotFound() {
    final String employee_id = "ANY_ID";
    final WrapperPostEnrollQuestionsRequest request = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.validatePreguntasEnroll(request, employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.processValidatePreguntasEnroll(employee_id, request);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void createPreguntasEnrollWhenReturnCreated() {
    final String employee_id = "ANY_ID";
    final WrapperPostEnrollQuestionsRequest request = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.createPreguntasEnroll(request, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, request);
    assertEquals(201, response.getStatusCode().value(), "should be return HTTP 201");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void createPreguntasEnrollWhenReturnBadRequest() {
    final String employee_id = "ANY_ID";
    final WrapperPostEnrollQuestionsRequest request = new WrapperPostEnrollQuestionsRequest();
    when(preguntasEnrollService.createPreguntasEnroll(request, employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, request);
    assertEquals(400, response.getStatusCode().value(), "should be return HTTP 400");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void getCatalogoPreguntasByEstatusWhenReturnOkAndTheResponseIsNotEmpty() {
    final String estatusPregunta = "A";
    WrapperQuestions questions = mock(WrapperQuestions.class);
    lenient().when(questions.getQuestionId()).thenReturn("1");
    lenient().when(questions.getDescription()).thenReturn("Fist question:");
    WrapperGetRetrieveQuestionsResponse wrapper = mock(WrapperGetRetrieveQuestionsResponse.class);
    lenient().when(wrapper.getQuestions()).thenReturn(List.of(questions));
    when(catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta)).thenReturn(wrapper);

    ResponseEntity<WrapperGetRetrieveQuestionsResponse> response =
      customerManagementController.getCatalogoPreguntasByEstatus(estatusPregunta);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertTrue(response.hasBody(), "has body");
  }

  @Test
  void getCatalogoPreguntasByEstatusWhenReturnOkAndTheResponseIsEmpty() {
    final String estatusPregunta = "A";
    final WrapperGetRetrieveQuestionsResponse wrapperGetRetrieveQuestionsResponse = mock(WrapperGetRetrieveQuestionsResponse.class);
    when(catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta)).thenReturn(wrapperGetRetrieveQuestionsResponse);

    ResponseEntity<WrapperGetRetrieveQuestionsResponse> response =
      customerManagementController.getCatalogoPreguntasByEstatus(estatusPregunta);
    assertEquals(404, response.getStatusCode().value(), "should be return HTTP 404");
    assertFalse(response.hasBody(), "has not body");
  }

  @Test
  void getSubordinadosByExpedienteWhenReturnIsOk() {
    final String employee_id = "ANY_ID";
    ListSubordinatesEmployee listSubordinatesEmployee = mock(ListSubordinatesEmployee.class);
    lenient().when(listSubordinatesEmployee.getNumber()).thenReturn("1");
    ListSubordinate listSubordinate = mock(ListSubordinate.class);
    lenient().when(listSubordinate.getEmployee()).thenReturn(listSubordinatesEmployee);
    WrapperRetrieveSubordinateResponse wrapperRetrieveSubordinateResponse = mock(WrapperRetrieveSubordinateResponse.class);
    lenient().when(wrapperRetrieveSubordinateResponse.getSubordinates()).thenReturn(List.of(listSubordinate));
    when(supervisorExpedienteService.getSubordinariosId(employee_id)).thenReturn(wrapperRetrieveSubordinateResponse);

    ResponseEntity<WrapperRetrieveSubordinateResponse> response = customerManagementController.getSubordinadosByExpediente(employee_id);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertTrue(response.hasBody(), "has body");
  }

  @Test
  void getSubordinadosByExpedienteWhenReturnIsNotContent() {
    final String employee_id = "ANY_ID";
    WrapperRetrieveSubordinateResponse wrapperRetrieveSubordinateResponse = mock(WrapperRetrieveSubordinateResponse.class);
    when(supervisorExpedienteService.getSubordinariosId(employee_id)).thenReturn(wrapperRetrieveSubordinateResponse);

    ResponseEntity<WrapperRetrieveSubordinateResponse> response = customerManagementController.getSubordinadosByExpediente(employee_id);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "has not body");
  }

  @Test
  void getPreguntasEnrollByExpedienteWhenReturnIsOk() {
    final String employee_id = "ANY_ID";
    QuestionnaireQuestion questionnaireQuestion = mock(QuestionnaireQuestion.class);
    lenient().when(questionnaireQuestion.getQuestionId()).thenReturn("1");
    Questionnaire questionnaire = mock(Questionnaire.class);
    lenient().when(questionnaire.getQuestion()).thenReturn(questionnaireQuestion);
    WrapperGetRetrieveEmployeeQuestionaireResponse wrapperGetRetrieveEmployeeQuestionaireResponse = mock(WrapperGetRetrieveEmployeeQuestionaireResponse.class);
    lenient().when(wrapperGetRetrieveEmployeeQuestionaireResponse.getQuestions()).thenReturn(List.of(questionnaire));
    lenient().when(preguntasEnrollService.getPreguntasEnrollByExpediente(employee_id)).thenReturn(wrapperGetRetrieveEmployeeQuestionaireResponse);

    ResponseEntity<WrapperGetRetrieveEmployeeQuestionaireResponse> response = customerManagementController.getPreguntasEnrollByExpediente(employee_id);
    assertEquals(200, response.getStatusCode().value(), "should be return HTTP 200");
    assertTrue(response.hasBody(), "has body");
  }

  @Test
  void getPreguntasEnrollByExpedienteWhenReturnIsNoContent() {
    final String employee_id = "ANY_ID";
    WrapperGetRetrieveEmployeeQuestionaireResponse wrapperGetRetrieveEmployeeQuestionaireResponse = mock(WrapperGetRetrieveEmployeeQuestionaireResponse.class);
    when(preguntasEnrollService.getPreguntasEnrollByExpediente(employee_id)).thenReturn(wrapperGetRetrieveEmployeeQuestionaireResponse);

    ResponseEntity<WrapperGetRetrieveEmployeeQuestionaireResponse> response = customerManagementController.getPreguntasEnrollByExpediente(employee_id);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "has body");
  }

  @Test
  void deletePreguntasEnrollByExpedienteWhenReturnNoContent() {
    final String employee_id = "ANY_ID";
    when(preguntasEnrollService.deletePreguntasEnrollByExpediente(employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.deletePreguntasEnrollByExpediente(employee_id);
    assertEquals(204, response.getStatusCode().value(), "should be return HTTP 204");
    assertFalse(response.hasBody(), "no has body");
  }

  @Test
  void deletePreguntasEnrollByExpedienteWhenReturnNotFound() {
    final String employee_id = "ANY_ID";
    when(preguntasEnrollService.deletePreguntasEnrollByExpediente(employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.deletePreguntasEnrollByExpediente(employee_id);
    assertEquals(404, response.getStatusCode().value(), "should be return HTTP 404");
    assertFalse(response.hasBody(), "no has body");
  }
}