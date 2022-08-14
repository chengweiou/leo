set search_path = leob;

DROP TABLE IF EXISTS notify;
CREATE TABLE notify (
  id bigserial NOT NULL,
  personId bigint NOT NULL,
  email text NOT NULL,
  sms text NOT NULL,
  phoneToken text NOT NULL,
  padToken text NOT NULL,
  activeEmail text NOT NULL,
  activeSms text NOT NULL,
  activePush text NOT NULL,
  createAt timestamp with time zone NOT NULL,
  updateAt timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS pushSpec;
CREATE TABLE pushSpec (
  id bigserial NOT NULL,
  personId bigint NOT NULL,
  type text NOT NULL,
  active boolean NOT NULL,
  num integer NOT NULL,
  createAt timestamp with time zone NOT NULL,
  updateAt timestamp with time zone NOT NULL,
  PRIMARY KEY (id)
);
