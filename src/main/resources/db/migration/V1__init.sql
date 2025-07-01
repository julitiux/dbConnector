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

CREATE TABLE catalogo_preguntas (
    no_pregunta         INTEGER PRIMARY KEY,
    desc_pregunta       VARCHAR(20) NOT NULL,
    estatus_pregunta    VARCHAR(20) NOT NULL,
    fecha_inicio        VARCHAR(20) NOT NULL,
    fecha_estatus       VARCHAR(20) NOT NULL
);

INSERT INTO catalogo_preguntas (no_pregunta, desc_pregunta, estatus_pregunta, fecha_inicio, fecha_estatus) VALUES
(1, '¿Fuma?', 'ACTIVO', '2024-01-01', '2024-01-01'),
(2, '¿Toma alcohol?', 'ACTIVO', '2024-01-01', '2024-01-01'),
(3, '¿Hace ejercicio?', 'ACTIVO', '2024-01-01', '2024-01-01'),
(4, '¿Alergias?', 'ACTIVO', '2024-01-01', '2024-01-01'),
(5, '¿Cirugías previas?', 'INACTIVO', '2023-06-01', '2024-06-01');

CREATE TABLE preguntas_enroll (
    expediente         VARCHAR(20)     NOT NULL,
    no_pregunta        INTEGER         NOT NULL,
    respuesta_pregunta VARCHAR(20),
    estatus            VARCHAR(20),
    fecha_hora         VARCHAR(20),
    CONSTRAINT pk_preguntas_enroll PRIMARY KEY (expediente, no_pregunta),
    CONSTRAINT fk_no_pregunta FOREIGN KEY (no_pregunta) REFERENCES catalogo_preguntas(no_pregunta)
);

INSERT INTO preguntas_enroll (expediente, no_pregunta, respuesta_pregunta, estatus, fecha_hora) VALUES
('EXP001', 1, 'Sí', 'ACTIVO', '2025-06-30 10:00'),
('EXP001', 2, 'No', 'ACTIVO', '2025-06-30 10:05'),
('EXP001', 3, 'Sí', 'ACTIVO', '2025-06-30 10:10'),
('EXP002', 1, 'No', 'ACTIVO', '2025-06-30 11:00'),
('EXP002', 4, 'Polen', 'ACTIVO', '2025-06-30 11:05');