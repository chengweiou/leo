set search_path = leonids;

DROP TABLE IF EXISTS device;
CREATE TABLE device (
  id bigserial NOT NULL,
  personId bigserial NOT NULL,
  token character varying NOT NULL,
  createAt timestamp without time zone NOT NULL,
  updateAt timestamp without time zone NOT NULL,
  PRIMARY KEY (id)
)