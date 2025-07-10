package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.AnswersDetailsQuestion;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaireRequest;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring",
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IWrapperPostEnrollmentQuestionnaireRequest {

  @Mapping(target = "id.noPregunta", source = "question.questionId")
  @Mapping(target = "respuestaPregunta", source = "question.answer")
  PreguntasEnroll mapToPreguntasEnrollToCompare(AnswersDetailsQuestion question);

  @Mapping(target = "id.noPregunta", source = "question.questionId")
  @Mapping(target = "id.expediente", source = "expediente")
  @Mapping(target = "respuestaPregunta", source = "question.answer")
  @Mapping(target = "estatus",  constant = "A")
  @Mapping(target = "fechaHora", expression = "java(this.getFechaHora())")
  PreguntasEnroll mapToPreguntasEnrollToSave(AnswersDetailsQuestion question, String expediente);

  default List<PreguntasEnroll> mapToPreguntasEnrollListToCompare(WrapperPostEnrollmentQuestionnaireRequest request) {
    return request.getQuestions().stream().map(question -> {
      return mapToPreguntasEnrollToCompare(question.getQuestion()) ;
    }).toList();
  }

  default List<PreguntasEnroll> mapToPreguntasEnrollListToSave(WrapperPostEnrollmentQuestionnaireRequest request, String expediente) {
    return request.getQuestions().stream().map(question -> {
      return mapToPreguntasEnrollToSave(question.getQuestion(), expediente) ;
    }).toList();
  }

  @Named("getFechaHora")
  default String getFechaHora(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy:HHmmss", Locale.ENGLISH);
    return LocalDateTime.now().format(formatter);
  }
}