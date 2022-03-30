CREATE SEQUENCE hibernate_sequence START WITH 1000 INCREMENT BY 1;

--######################################################################################################################

CREATE TABLE date_time2entity
(
    id    BIGINT    NOT NULL PRIMARY KEY,
    value DATETIME2 NOT NULL DEFAULT SYSDATETIMEOFFSET(),
);

--######################################################################################################################

CREATE TABLE date_time_offset_entity
(
    id    BIGINT         NOT NULL PRIMARY KEY,
    value DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
);

--######################################################################################################################
