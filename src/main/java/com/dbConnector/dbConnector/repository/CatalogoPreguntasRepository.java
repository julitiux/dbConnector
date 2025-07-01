package com.dbConnector.dbConnector.repository;

import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoPreguntasRepository extends JpaRepository<CatalogoPreguntas, Integer> {
}
