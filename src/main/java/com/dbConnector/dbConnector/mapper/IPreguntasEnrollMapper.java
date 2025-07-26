package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.domain.PreguntaDescripcionDTO;
import com.dbConnector.dbConnector.model.request.WrapperAnswer;
import com.dbConnector.dbConnector.model.request.WrapperPostEnrollQuestionsRequest;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveEmployeeQuestionsResponse;
import com.dbConnector.dbConnector.model.response.WrapperQuestions;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring",
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPreguntasEnrollMapper {

  @Mapping(target = "id.noPregunta", source = "questionId")
  @Mapping(target = "respuestaPregunta", source = "answer")
  PreguntasEnroll mapToPreguntasEnrollToCompare(WrapperAnswer answer);

  @Mapping(target = "id.noPregunta", source = "answer.questionId")
  @Mapping(target = "id.expediente", source = "expediente")
  @Mapping(target = "respuestaPregunta", source = "answer.answer")
  @Mapping(target = "estatus", constant = "A")
  @Mapping(target = "fechaHora", expression = "java(this.getFechaHora())")
  PreguntasEnroll mapToPreguntasEnrollToSave(WrapperAnswer answer, String expediente);

  default List<PreguntasEnroll> mapToPreguntasEnrollListToCompare(WrapperPostEnrollQuestionsRequest request) {
    return request.getQuestions().stream()
      .map(this::mapToPreguntasEnrollToCompare)
      .toList();
  }

  default List<PreguntasEnroll> mapToPreguntasEnrollListToSave(WrapperPostEnrollQuestionsRequest request, String expediente) {
    return request.getQuestions().stream()
      .map(answer -> mapToPreguntasEnrollToSave(answer, expediente))
      .toList();
  }

  @Named("getFechaHora")
  default String getFechaHora() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HHmmss", Locale.ENGLISH);
    return LocalDateTime.now().format(formatter);
  }

  @Mapping(target = "questionId", source = "noPregunta")
  @Mapping(target = "description", source = "descPregunta")
  WrapperQuestions mapToPreguntasEnroll(PreguntaDescripcionDTO pregunta);

  default WrapperGetRetrieveEmployeeQuestionsResponse mapToWrapperGetRetrieveEmployeeQuestionsResponse(List<PreguntaDescripcionDTO> preguntasEnrolls) {

    List<WrapperQuestions> questions =
      preguntasEnrolls.stream()
        .map(this::mapToPreguntasEnroll)
        .toList();
    return new WrapperGetRetrieveEmployeeQuestionsResponse(questions);
  }
}