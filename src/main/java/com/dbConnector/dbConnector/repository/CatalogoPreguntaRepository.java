package com.dbConnector.dbConnector.repository;

import com.dbConnector.dbConnector.domain.CatalogoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoPreguntaRepository extends JpaRepository<Integer, CatalogoPregunta> {
}
