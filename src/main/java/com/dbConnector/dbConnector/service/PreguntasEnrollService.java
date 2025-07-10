package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.mapper.IWrapperPostEnrollmentQuestionnaireRequest;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaireRequest;
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
  private final IWrapperPostEnrollmentQuestionnaireRequest answerDetailsQuestion;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HHmmss", Locale.ENGLISH);

  public PreguntasEnrollService(PreguntasEnrollRepository preguntasEnrollRepository, IWrapperPostEnrollmentQuestionnaireRequest answerDetailsQuestion) {
    this.preguntasEnrollRepository = preguntasEnrollRepository;
    this.answerDetailsQuestion = answerDetailsQuestion;
  }

  public Boolean validatePreguntasEnroll(WrapperPostEnrollmentQuestionnaireRequest request, String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = answerDetailsQuestion.mapToPreguntasEnrollListToCompare(request);
    List<PreguntasEnroll> preguntasEnrollsSaved = preguntasEnrollRepository.findByIdExpediente(expediente);

    return preguntasEnrollsSaved.equals(preguntasEnrolls);
  }

  public List<PreguntasEnroll> createPreguntasEnroll(List<PreguntasEnroll> preguntasEnrolls, String expediente) {

    preguntasEnrolls.forEach(pregunta -> {
      pregunta.getId().setExpediente(expediente);
      pregunta.setEstatus("A");
      pregunta.setFechaHora(LocalDateTime.now().format(formatter));
    });
    return preguntasEnrollRepository.saveAll(preguntasEnrolls);
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

  public List<Integer> getPreguntasEnrollByExpediente(String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = preguntasEnrollRepository.findByIdExpediente(expediente);
    List<Integer> preguntasIds = new java.util.ArrayList<>(preguntasEnrolls.stream()
      .map(pregunta -> pregunta.getId().getNoPregunta())
      .toList());
    Collections.shuffle(preguntasIds);
    return preguntasIds.stream().limit(3).toList();
  }
}
