package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.AnswersDetails;
import com.dbConnector.dbConnector.model.AnswersDetailsQuestion;
import com.dbConnector.dbConnector.model.WrapperPostEnrollmentQuestionnaireRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IWrapperPostEnrollmentQuestionnaireRequestTest {

  private final IWrapperPostEnrollmentQuestionnaireRequest mapper = Mappers.getMapper(IWrapperPostEnrollmentQuestionnaireRequest.class);

  @Test
  void to_withValidWrapper_returnsMappedList() {
    // Arrange
    AnswersDetailsQuestion question = mock(AnswersDetailsQuestion.class);
    when(question.getQuestionId()).thenReturn("2");
    when(question.getAnswer()).thenReturn("No");

    // Act
    PreguntasEnroll result = mapper.mapToPreguntasEnroll(question);

    // Assert
//    assertThat(result).isNotNull();
//    assertThat(result.getId()).isNotNull();
    assertThat(result.getId().getNoPregunta()).isEqualTo(2);
    assertThat(result.getRespuestaPregunta()).isEqualTo("No");
  }

  @Test
  void toPreguntasEnroll_withValidWrapper_returnsMappedList() {
    // Arrange
    AnswersDetailsQuestion question = mock(AnswersDetailsQuestion.class);
    when(question.getQuestionId()).thenReturn("5");
    when(question.getAnswer()).thenReturn("Yes");

    AnswersDetails details = mock(AnswersDetails.class);
    when(details.getQuestion()).thenReturn(question);

    WrapperPostEnrollmentQuestionnaireRequest wrapper = mock(WrapperPostEnrollmentQuestionnaireRequest.class);
    when(wrapper.getQuestions()).thenReturn(List.of(details));

    // Act
    List<PreguntasEnroll> result = mapper.mapToPreguntasEnrollList(wrapper);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    PreguntasEnroll mapped = result.get(0);
    assertNotNull(mapped.getId());
    assertEquals(5, mapped.getId().getNoPregunta());
    assertEquals("Yes", mapped.getRespuestaPregunta());
  }
}