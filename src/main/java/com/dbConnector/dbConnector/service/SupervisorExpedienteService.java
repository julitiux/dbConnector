package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.SupervisorExpediente;
import com.dbConnector.dbConnector.mapper.ISupervisorExpedienteMapper;
import com.dbConnector.dbConnector.model.response.WrapperRetrieveSubordinateResponse;
import com.dbConnector.dbConnector.repository.SupervisorExpedienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupervisorExpedienteService {

  final private SupervisorExpedienteRepository supervisorExpedienteRepository;
  final private ISupervisorExpedienteMapper iSupervisorExpedienteMapper;

  public WrapperRetrieveSubordinateResponse getSubordinariosId(String expediente) {

    List<SupervisorExpediente> supervisorExpedientes = supervisorExpedienteRepository.findByExpediente(expediente);
    return iSupervisorExpedienteMapper.mapToWrapperRetrieveSubordinateResponse(supervisorExpedientes);
  }
}
