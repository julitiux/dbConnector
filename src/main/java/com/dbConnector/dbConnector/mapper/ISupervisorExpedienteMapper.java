package com.dbConnector.dbConnector.mapper;

import com.dbConnector.dbConnector.domain.SupervisorExpediente;
import com.dbConnector.dbConnector.model.response.WrapperGetRetrieveSubordinatesResponse;
import com.dbConnector.dbConnector.model.response.WrapperSubordinates;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface ISupervisorExpedienteMapper {

  default WrapperGetRetrieveSubordinatesResponse mapToWrapperRetrieveSubordinateResponse(
    List<SupervisorExpediente> supervisorExpedientes) {

    List<WrapperSubordinates> subordinates =
      supervisorExpedientes.stream()
        .map(supervisorExpediente -> new WrapperSubordinates(supervisorExpediente.getExpAgente()))
        .toList();
    return new WrapperGetRetrieveSubordinatesResponse(subordinates);
  }
}
