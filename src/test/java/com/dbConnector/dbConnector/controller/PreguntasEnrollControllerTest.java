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


@ExtendWith(MockitoExtension.class)
class PreguntasEnrollControllerTest {

  @Mock
  private PreguntasEnrollService preguntasEnrollService;
  @Mock
  private CatalogoPreguntasService catalogoPreguntasService;
  @Mock
  private SupervisorExpedienteService supervisorExpedienteService;
  @InjectMocks
  private PreguntasEnrollController preguntasEnrollController;

  @Test
  void processValidatePreguntasEnroll() {
    preguntasEnrollController.processValidatePreguntasEnroll("pathVariable", new WrapperPostEnrollmentQuestionnaireRequest());
  }

  @Test
  void createPreguntasEnroll() {
    preguntasEnrollController.createPreguntasEnroll("employee_id", new WrapperPostEnrollmentQuestionnaireRequest());
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