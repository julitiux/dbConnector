package com.dbConnector.dbConnector.controller;

import com.dbConnector.dbConnector.model.request.WrapperPostEnrollmentQuestionnaireRequest;
import com.dbConnector.dbConnector.model.response.QuestionsDetails;
import com.dbConnector.dbConnector.model.response.QuestionsDetailsQuestion;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveQuestionsResponse;
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
  void createPreguntasEnrollWhenReturnCreated() {

    final String employee_id = "ANY_ID";
    final WrapperPostEnrollmentQuestionnaireRequest request = new WrapperPostEnrollmentQuestionnaireRequest();

    when(preguntasEnrollService.createPreguntasEnroll(request, employee_id)).thenReturn(Boolean.TRUE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, request);

    assertEquals(201, response.getStatusCode().value(), "should be return HTTP 201");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void createPreguntasEnrollWhenReturnBadRequest() {

    final String employee_id = "ANY_ID";
    final WrapperPostEnrollmentQuestionnaireRequest request = new WrapperPostEnrollmentQuestionnaireRequest();

    when(preguntasEnrollService.createPreguntasEnroll(request, employee_id)).thenReturn(Boolean.FALSE);

    ResponseEntity<Void> response = customerManagementController.createPreguntasEnroll(employee_id, request);

    assertEquals(400, response.getStatusCode().value(), "should be return HTTP 400");
    assertFalse(response.hasBody(), "dont has body");
  }

  @Test
  void getCatalogoPreguntasByEstatusWhenReturnOkAndTheResponseIsNotEmpty() {

    final String estatusPregunta = "A";
    QuestionsDetailsQuestion questionsDetailsQuestion = mock(QuestionsDetailsQuestion.class);
    lenient().when(questionsDetailsQuestion.getQuestionId()).thenReturn("1");
    lenient().when(questionsDetailsQuestion.getDescription()).thenReturn("Fist question:");
    QuestionsDetails questionsDetails = mock(QuestionsDetails.class);
    lenient().when(questionsDetails.getQuestion()).thenReturn(questionsDetailsQuestion);
    WrapperGetRetrieveQuestionsResponse wrapperGetRetrieveQuestionsResponse = mock(WrapperGetRetrieveQuestionsResponse.class);
    lenient().when(wrapperGetRetrieveQuestionsResponse.getQuestions()).thenReturn(List.of(questionsDetails));

    when(catalogoPreguntasService.getPreguntasByEstatus(estatusPregunta)).thenReturn(wrapperGetRetrieveQuestionsResponse);

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
  void getSubordinadosByExpediente() {
  }

  @Test
  void getPreguntasEnrollByExpediente() {
  }

  @Test
  void deletePreguntasEnrollByExpediente() {
  }
}