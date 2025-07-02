package com.dbConnector.dbConnector.repository;

import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogoPreguntasRepository extends JpaRepository<CatalogoPreguntas, Integer> {

  List<CatalogoPreguntas> findByEstatusPregunta(String estatusPregunta);

}
