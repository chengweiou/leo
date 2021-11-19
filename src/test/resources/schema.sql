set search_path = leob;

DROP TABLE IF EXISTS device;
CREATE TABLE device (
  id bigserial NOT NULL,
  personId bigserial NOT NULL,
  token character varying NOT NULL,
  active boolean NOT NULL,
  createAt timestamp with time zone NOT NULL,
  updateAt timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
)
