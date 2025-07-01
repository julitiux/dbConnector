package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.repository.PreguntasEnrollRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntasEnrollService {

  private final PreguntasEnrollRepository preguntasEnrollRepository;

  public PreguntasEnrollService(PreguntasEnrollRepository preguntasEnrollRepository) {
    this.preguntasEnrollRepository = preguntasEnrollRepository;
  }

  public void deletePreguntasEnrollByExpediente(String expediente) {

    List<PreguntasEnroll> preguntasEnrolls = preguntasEnrollRepository.findByIdExpediente(expediente);
    if (!preguntasEnrolls.isEmpty())
      preguntasEnrollRepository.deleteAll(preguntasEnrolls);
    else
      throw new RuntimeException();
  }
}
