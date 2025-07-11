package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.SupervisorExpediente;
import com.dbConnector.dbConnector.model.response.ListSubordinate;
import com.dbConnector.dbConnector.model.response.ListSubordinatesEmployee;
import com.dbConnector.dbConnector.model.response.WrapperRetrieveSubordinateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface SupervisorExpedienteMapper {

  default WrapperRetrieveSubordinateResponse mapToWrapperRetrieveSubordinateResponse(List<SupervisorExpediente> supervisorExpedientes) {

    List<ListSubordinate> subordinates = supervisorExpedientes.stream()
      .map(supervisorExpediente -> {
        return new ListSubordinate(new ListSubordinatesEmployee(supervisorExpediente.getExpAgente()));
      }).toList();
    return new WrapperRetrieveSubordinateResponse(subordinates);
  }
}
