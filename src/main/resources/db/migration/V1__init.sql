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


CREATE TABLE preguntas_enroll (
    expediente         VARCHAR(20)     NOT NULL,
    no_pregunta        INTEGER         NOT NULL,
    respuesta_pregunta VARCHAR(20),
    estatus            VARCHAR(20),
    fecha_hora         VARCHAR(20),
    CONSTRAINT pk_preguntas_enroll PRIMARY KEY (expediente, no_pregunta)
);