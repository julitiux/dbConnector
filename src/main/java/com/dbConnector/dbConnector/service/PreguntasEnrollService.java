package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.mapper.IWrapperPostEnrollmentQuestionnaireMapper;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaireRequest;
import com.dbConnector.dbConnector.repository.PreguntasEnrollRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PreguntasEnrollService {

  private final PreguntasEnrollRepository preguntasEnrollRepository;
  private final IWrapperPostEnrollmentQuestionnaireMapper iWrapperPostEnrollmentQuestionnaireMapper;

  public Boolean validatePreguntasEnroll(WrapperPostEnrollmentQuestionnaireRequest request, String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = iWrapperPostEnrollmentQuestionnaireMapper.mapToPreguntasEnrollListToCompare(request);
    List<PreguntasEnroll> preguntasEnrollsSaved = preguntasEnrollRepository.findByIdExpediente(expediente);
    return preguntasEnrollsSaved.equals(preguntasEnrolls);
  }

  public Boolean createPreguntasEnroll(WrapperPostEnrollmentQuestionnaireRequest request, String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = iWrapperPostEnrollmentQuestionnaireMapper.mapToPreguntasEnrollListToSave(request, expediente);
    List<PreguntasEnroll> preguntasEnrollsCreated = preguntasEnrollRepository.saveAll(preguntasEnrolls);
    if (preguntasEnrollsCreated != null && !preguntasEnrollsCreated.isEmpty()) {
      return true;
    } else {
      return false;
    }
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
