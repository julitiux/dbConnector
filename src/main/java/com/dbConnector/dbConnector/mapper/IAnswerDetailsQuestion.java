package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.AnswersDetailsQuestion;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaireResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAnswerDetailsQuestion {

  @Mapping(target = "id.noPregunta", source = "question.questionId")
  @Mapping(target = "respuestaPregunta", source = "question.answer")
  PreguntasEnroll mapToPreguntasEnroll(AnswersDetailsQuestion question);

  default List<PreguntasEnroll> mapToPreguntasEnrollList(WrapperPostEnrollmentQuestionnaireResponse request) {
    return request.getQuestions().stream().map(question -> {
      return mapToPreguntasEnroll(question.getQuestion());
    }).toList();
  }
}
