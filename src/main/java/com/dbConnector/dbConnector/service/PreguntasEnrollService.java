package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.repository.PreguntasEnrollRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PreguntasEnrollService {

  private final PreguntasEnrollRepository preguntasEnrollRepository;

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HHmmss", Locale.ENGLISH);

  public PreguntasEnrollService(PreguntasEnrollRepository preguntasEnrollRepository) {
    this.preguntasEnrollRepository = preguntasEnrollRepository;
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

  public List<PreguntasEnroll> validatePreguntasEnroll(List<PreguntasEnroll> preguntasEnrolls, String expediente) {

    List<PreguntasEnroll> preguntasEnrollsSaved = preguntasEnrollRepository.findByIdExpediente(expediente);

    // TODO: Preguntar si los campos a comprar solo seran expediente, noPregunta y respuestaPregunta
    // TODO: Verificar por que solo expediente es obligatorio y no los otros campos

    Set<String> savedKeys = preguntasEnrollsSaved.stream()
      .map(pe -> pe.getId().getExpediente() + "-" + pe.getId().getNoPregunta() + "-" + pe.getRespuestaPregunta())
      .collect(Collectors.toSet());

    // Filter input list by matching keys
    return preguntasEnrolls.stream()
      .filter(pe -> savedKeys.contains(
        pe.getId().getExpediente() + "-" + pe.getId().getNoPregunta() + "-" + pe.getRespuestaPregunta()))
      .toList();
  }
}
