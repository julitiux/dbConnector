package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.request.WrapperAnswer;
import com.dbConnector.dbConnector.model.request.WrapperPostEnrollQuestionsRequest;
import com.dbConnector.dbConnector.model.response.Questionnaire;
import com.dbConnector.dbConnector.model.response.QuestionnaireQuestion;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveEmployeeQuestionaireResponse;
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

  @Mapping(target = "id.noPregunta", source = "answer.questionId")
  @Mapping(target = "respuestaPregunta", source = "answer.answer")
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

  default WrapperGetRetrieveEmployeeQuestionaireResponse mapToWrapperGetRetrieveEmployeeQuestionaireResponse(List<PreguntasEnroll> preguntasEnrolls) {

    List<Questionnaire> questions = new java.util.ArrayList<>(preguntasEnrolls.stream()
      .map(pregunta -> {
        return new Questionnaire(
          new QuestionnaireQuestion(pregunta.getId().getNoPregunta().toString())
        );
      }).toList());
    return new WrapperGetRetrieveEmployeeQuestionaireResponse(questions);
  }
}