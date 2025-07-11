package com.dbConnector.dbConnector.service;

import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import com.dbConnector.dbConnector.mapper.ICatalogoPreguntasMapper;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveQuestionsResponse;
import com.dbConnector.dbConnector.repository.CatalogoPreguntasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CatalogoPreguntasService {

  private final CatalogoPreguntasRepository catalogoPreguntasRepository;
  private final ICatalogoPreguntasMapper iCatalogoPreguntasMapper;

  public WrapperGetRetrieveQuestionsResponse getPreguntasByEstatus(String estatusPregunta) {
    List<CatalogoPreguntas> catalogoPreguntas = catalogoPreguntasRepository.findByEstatusPregunta(estatusPregunta);
    Collections.shuffle(catalogoPreguntas);
    List<CatalogoPreguntas> catalogoPreguntas5 =  catalogoPreguntas.stream().limit(5).toList();
    return iCatalogoPreguntasMapper.mapToWrapperGetRetrieveQuestionsResponse(catalogoPreguntas5);
  }
}
