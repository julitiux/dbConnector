package com.dbConnector.dbConnector.repository;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.domain.PreguntasEnrollId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntasEnrollRepository extends JpaRepository<PreguntasEnroll, PreguntasEnrollId> {

  List<PreguntasEnroll> findByIdExpediente(String expediente);
  
}
