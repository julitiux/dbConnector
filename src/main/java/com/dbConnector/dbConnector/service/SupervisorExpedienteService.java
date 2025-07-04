package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.SupervisorExpediente;
import com.dbConnector.dbConnector.repository.SupervisorExpedienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorExpedienteService {

  final private SupervisorExpedienteRepository supervisorExpedienteRepository;

  public SupervisorExpedienteService(SupervisorExpedienteRepository supervisorExpedienteRepository) {
    this.supervisorExpedienteRepository = supervisorExpedienteRepository;
  }

  public List<String> getSubordinariosId(String expediente) {

    List<SupervisorExpediente> supervisorExpedientes = supervisorExpedienteRepository.findByExpediente(expediente);

    return supervisorExpedientes.stream().map(SupervisorExpediente::getExpAgente).toList();
  }

}
