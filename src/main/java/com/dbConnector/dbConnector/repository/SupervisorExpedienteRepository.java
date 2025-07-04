package com.dbConnector.dbConnector.repository;

import com.dbConnector.dbConnector.domain.SupervisorExpediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisorExpedienteRepository extends JpaRepository<SupervisorExpediente, String> {

  List<SupervisorExpediente> findByExpediente(String expediente);

}
