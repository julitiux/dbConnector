package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.PreguntasEnroll;
import com.dbConnector.dbConnector.model.request.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IWrapperPostEnrollmentQuestionnaireRequestTest {

  private final IPreguntasEnrollMapper mapper = Mappers.getMapper(IPreguntasEnrollMapper.class);

  @Test
  void to_withValidWrapper_returnsMappedList() {
    // Arrange
    WrapperAnswer wrapperAnswer = mock(WrapperAnswer.class);
    when(wrapperAnswer.getQuestionId()).thenReturn("2");
    when(wrapperAnswer.getAnswer()).thenReturn("No");

    // Act
    PreguntasEnroll result = mapper.mapToPreguntasEnrollToCompare(wrapperAnswer);

    // Assert
    assertThat(result).isNotNull();
    assertThat(result.getId()).isNotNull();
    assertThat(result.getId().getNoPregunta()).isEqualTo(2);
    assertThat(result.getRespuestaPregunta()).isEqualTo("No");
  }

  @Test
  void toPreguntasEnroll_withValidWrapper_returnsMappedList() {
    // Arrange
    WrapperAnswer wrapperAnswer = mock(WrapperAnswer.class);
    when(wrapperAnswer.getQuestionId()).thenReturn("5");
    when(wrapperAnswer.getAnswer()).thenReturn("Yes");

    WrapperPostEnrollQuestionsRequest wrapper = mock(WrapperPostEnrollQuestionsRequest.class);
    when(wrapper.getQuestions()).thenReturn(List.of(wrapperAnswer));

    // Act
    List<PreguntasEnroll> result = mapper.mapToPreguntasEnrollListToCompare(wrapper);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    PreguntasEnroll mapped = result.get(0);
    assertNotNull(mapped.getId());
    assertEquals(5, mapped.getId().getNoPregunta());
    assertEquals("Yes", mapped.getRespuestaPregunta());
  }
}