CREATE TABLE people
(
    id           UUID           NOT NULL,
    username     VARCHAR UNIQUE NOT NULL,
    name         VARCHAR        NOT NULL,
    lastname     VARCHAR        NOT NULL,
    password     VARCHAR        NOT NULL,
    date_created VARCHAR        NOT NULL,
    last_updated VARCHAR        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE sd_catalogo_preguntas (
    no_pregunta         INTEGER PRIMARY KEY,
    desc_pregunta       VARCHAR(20) NOT NULL,
    estatus_pregunta    VARCHAR(20) NOT NULL,
    fecha_inicio        VARCHAR(20) NOT NULL,
    fecha_estatus       VARCHAR(20) NOT NULL
);

INSERT INTO sd_catalogo_preguntas (no_pregunta, desc_pregunta, estatus_pregunta, fecha_inicio, fecha_estatus) VALUES
(1, '¿Fuma?', 'A', '2024-01-01', '2024-01-01'),
(2, '¿Toma alcohol?', 'A', '2024-01-01', '2024-01-01'),
(3, '¿Hace ejercicio?', 'A', '2024-01-01', '2024-01-01'),
(4, '¿Alergias?', 'A', '2024-01-01', '2024-01-01'),
(5, '¿Cirugías previas?', 'D', '2023-06-01', '2024-06-01');

CREATE TABLE sd_preguntas_enroll (
    expediente         VARCHAR(20)     NOT NULL,
    no_pregunta        INTEGER         NOT NULL,
    respuesta_pregunta VARCHAR(20),
    estatus            VARCHAR(20),
    fecha_hora         VARCHAR(20),
    CONSTRAINT pk_preguntas_enroll PRIMARY KEY (expediente, no_pregunta),
    CONSTRAINT fk_no_pregunta FOREIGN KEY (no_pregunta) REFERENCES sd_catalogo_preguntas(no_pregunta)
);

INSERT INTO sd_preguntas_enroll (expediente, no_pregunta, respuesta_pregunta, estatus, fecha_hora) VALUES
('EXP001', 1, 'Sí', 'A', '2025-06-30 10:00'),
('EXP001', 2, 'No', 'A', '2025-06-30 10:05'),
('EXP001', 3, 'Sí', 'A', '2025-06-30 10:10'),
('EXP002', 1, 'No', 'A', '2025-06-30 11:00'),
('EXP002', 4, 'Polen', 'A', '2025-06-30 11:05');


CREATE TABLE sd_supervisor_expediente (
    callid            VARCHAR(50)    NOT NULL,
    expediente        VARCHAR(20),
    exp_agente        VARCHAR(20),
    PRIMARY KEY (callid)
);

INSERT INTO sd_supervisor_expediente(callid, expediente, exp_agente) VALUES
('1', 'EXP001', 'AGENTE0011'),
('2', 'EXP001', 'AGENTE0012'),
('3', 'EXP001', 'AGENTE0013'),
('4', 'EXP001', 'AGENTE0014'),
('5', 'EXP002', 'AGENTE0020'),
('6', 'EXP002', 'AGENTE0021'),
('7', 'EXP002', 'AGENTE0022'),
('8', 'EXP002', 'AGENTE0023'),
('9', 'EXP002', 'AGENTE0024'),
('10', 'EXP002', 'AGENTE0024');