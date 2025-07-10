package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.mapper.IAnswerDetailsQuestion;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaireResponse;
import com.dbConnector.dbConnector.repository.PreguntasEnrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class PreguntasEnrollService {

  private final PreguntasEnrollRepository preguntasEnrollRepository;
  private final IAnswerDetailsQuestion answerDetailsQuestion;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HHmmss", Locale.ENGLISH);

  public PreguntasEnrollService(PreguntasEnrollRepository preguntasEnrollRepository, IAnswerDetailsQuestion answerDetailsQuestion) {
    this.preguntasEnrollRepository = preguntasEnrollRepository;
    this.answerDetailsQuestion = answerDetailsQuestion;
  }

  public Boolean validatePreguntasEnroll(WrapperPostEnrollmentQuestionnaireResponse request, String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = answerDetailsQuestion.mapToPreguntasEnrollList(request);
    List<PreguntasEnroll> preguntasEnrollsSaved = preguntasEnrollRepository.findByIdExpediente(expediente);

    return preguntasEnrollsSaved.equals(preguntasEnrolls);
  }

  public Boolean deletePreguntasEnrollByExpediente(String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = preguntasEnrollRepository.findByIdExpediente(expediente);
    if (!preguntasEnrolls.isEmpty()) {
      preguntasEnrollRepository.deleteAll(preguntasEnrolls);
      return true;
    } else {
      return false;
    }
  }

  public List<PreguntasEnroll> createPreguntasEnroll(List<PreguntasEnroll> preguntasEnrolls, String expediente) {

//    TODO: Preguntar que tipo de logica llevara: Que pasa cuando ya existen preguntas enroll para un expediente?
//    TODO: El flujo correcto es actualizar las preguntas enroll si ya existen para el expediente, o crear nuevas si no existen.
    preguntasEnrolls.forEach(pregunta -> {
      pregunta.getId().setExpediente(expediente);
      pregunta.setEstatus("A");
      pregunta.setFechaHora(LocalDateTime.now().format(formatter));
    });
    return preguntasEnrollRepository.saveAll(preguntasEnrolls);
  }

  public List<Integer> getPreguntasEnrollByExpediente(String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = preguntasEnrollRepository.findByIdExpediente(expediente);
    List<Integer> preguntasIds = new java.util.ArrayList<>(preguntasEnrolls.stream()
      .map(pregunta -> pregunta.getId().getNoPregunta())
      .toList());
    Collections.shuffle(preguntasIds);
    return preguntasIds.stream().limit(3).toList();
  }
}
