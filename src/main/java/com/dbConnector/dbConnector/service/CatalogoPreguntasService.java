package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import com.dbConnector.dbConnector.repository.CatalogoPreguntasRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CatalogoPreguntasService {

  private final CatalogoPreguntasRepository catalogoPreguntasRepository;

  public CatalogoPreguntasService(CatalogoPreguntasRepository catalogoPreguntasRepository) {
    this.catalogoPreguntasRepository = catalogoPreguntasRepository;
  }

  public List<CatalogoPreguntas> getPreguntasByEstatus(String estatusPregunta) {
    List<CatalogoPreguntas> catalogoPreguntas = catalogoPreguntasRepository.findByEstatusPregunta(estatusPregunta);
    Collections.shuffle(catalogoPreguntas);
    return catalogoPreguntas.stream().limit(5).toList();
  }
}
