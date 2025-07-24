package com.dbConnector.dbConnector.repository;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.domain.PreguntasEnrollId;
import com.dbConnector.dbConnector.model.domain.PreguntaDescripcionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntasEnrollRepository extends JpaRepository<PreguntasEnroll, PreguntasEnrollId> {

  List<PreguntasEnroll> findByIdExpediente(String expediente);

  @Query("SELECT new com.dbConnector.dbConnector.model.domain.PreguntaDescripcionDTO(p.id.noPregunta, p.catalogoPreguntas.descPregunta) " +
    "FROM PreguntasEnroll p " +
    "WHERE p.id.expediente = :expediente")
  List<PreguntaDescripcionDTO> findPreguntasYDescripcionByExpediente(@Param("expediente") String expediente);
}
