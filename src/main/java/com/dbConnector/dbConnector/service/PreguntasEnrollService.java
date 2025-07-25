package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.mapper.IPreguntasEnrollMapper;
import com.dbConnector.dbConnector.model.domain.PreguntaDescripcionDTO;
import com.dbConnector.dbConnector.model.request.WrapperPostEnrollQuestionsRequest;
import com.dbConnector.dbConnector.model.request.WrapperPostEnrollmentQuestionnaireRequest;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveEmployeeQuestionaireResponse;
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
  private final IPreguntasEnrollMapper iPreguntasEnrollMapper;

  public Boolean validatePreguntasEnroll(WrapperPostEnrollQuestionsRequest request, String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = iPreguntasEnrollMapper.mapToPreguntasEnrollListToCompare(request);
    List<PreguntasEnroll> preguntasEnrollsSaved = preguntasEnrollRepository.findByIdExpediente(expediente);
    return preguntasEnrollsSaved.equals(preguntasEnrolls);
  }

  public Boolean createPreguntasEnroll(WrapperPostEnrollQuestionsRequest request, String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = iPreguntasEnrollMapper.mapToPreguntasEnrollListToSave(request, expediente);
    List<PreguntasEnroll> preguntasEnrollsCreated = preguntasEnrollRepository.saveAll(preguntasEnrolls);
    if (preguntasEnrollsCreated != null && !preguntasEnrollsCreated.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public WrapperGetRetrieveEmployeeQuestionaireResponse getPreguntasEnrollByExpediente(String expediente) {

    List<PreguntaDescripcionDTO> preguntaDescripcionDto =
      preguntasEnrollRepository.findPreguntasYDescripcionByExpediente(expediente);

    log.info(">>>>");
    preguntaDescripcionDto.forEach(System.out::println);

    List<PreguntasEnroll> preguntasEnrolls = preguntasEnrollRepository.findByIdExpediente(expediente);
    Collections.shuffle(preguntasEnrolls);
    List<PreguntasEnroll> preguntasEnrolls3 = preguntasEnrolls.stream().limit(3).toList();
    return iPreguntasEnrollMapper.mapToWrapperGetRetrieveEmployeeQuestionaireResponse(preguntasEnrolls3);
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
}
