package com.dbConnector.dbConnector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sd_supervisor_expediente")
public class SupervisorExpediente {

  @Id
  private String callid;

  @Column(name = "expediente", nullable = true, length = 20)
  private String expediente;

  @Column(name = "exp_agente", nullable = true, length = 20)
  private String expAgente;

}
