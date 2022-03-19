set search_path = leob;

DROP TABLE IF EXISTS device;
CREATE TABLE device (
  id bigserial NOT NULL,
  personId bigint NOT NULL,
  token text NOT NULL,
  active boolean NOT NULL,
  createAt timestamp with time zone NOT NULL,
  updateAt timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS notify;
CREATE TABLE notify (
  id bigserial NOT NULL,
  personId bigint NOT NULL,
  type text NOT NULL,
  active boolean NOT NULL,
  num integer NOT NULL,
  createAt timestamp with time zone NOT NULL,
  updateAt timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);
