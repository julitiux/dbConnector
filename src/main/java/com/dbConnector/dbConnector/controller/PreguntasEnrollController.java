package com.dbConnector.dbConnector.controller;


import com.dbConnector.dbConnector.service.PreguntasEnrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/preguntas-enroll")
public class PreguntasEnrollController {

  private final PreguntasEnrollService preguntasEnrollService;

  public PreguntasEnrollController(PreguntasEnrollService preguntasEnrollService) {
    this.preguntasEnrollService = preguntasEnrollService;
  }

  @DeleteMapping("/{expediente}/delete")
  public ResponseEntity<Void> deletePreguntasEnrollByExpediente(@PathVariable("expediente") String expediente) {
    log.info("Deleting preguntas enroll for expediente: {}", expediente);

    preguntasEnrollService.deletePreguntasEnrollByExpediente(expediente);
    return ResponseEntity.noContent().build();
  }
}
