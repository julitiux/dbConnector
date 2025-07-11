package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.CatalogoPreguntas;
import com.dbConnector.dbConnector.model.response.QuestionsDetails;
import com.dbConnector.dbConnector.model.response.QuestionsDetailsQuestion;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveQuestionsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ICatalogoPreguntasMapper {

  @Mapping(target = "questionId", source = "noPregunta")
  @Mapping(target = "description", source = "descPregunta")
  QuestionsDetailsQuestion mapToQuestionsDetailsQuestion(CatalogoPreguntas preguntas);


  default WrapperGetRetrieveQuestionsResponse mapToWrapperGetRetrieveQuestionsResponse(List<CatalogoPreguntas> preguntas) {

    List<QuestionsDetails> questions = preguntas.stream()
      .map(pregunta -> new QuestionsDetails(mapToQuestionsDetailsQuestion(pregunta))).toList();
    return new WrapperGetRetrieveQuestionsResponse(questions);
  }
}
