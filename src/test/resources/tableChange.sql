
-- alter table tablename rename to oldtablename;
-- create table tablename (column defs go here);
-- insert into tablename (col1, col2, col3) select col2, col1, col3 from oldtablename;
-- SELECT setval(pg_get_serial_sequence('tablename', 'id'), coalesce(max(id), 0)+1 , false) FROM tablename;
-- DROP TABLE IF EXISTS oldtablename;

set search_path = leob;

alter table notify rename to pushSpec;
SELECT setval(pg_get_serial_sequence('pushSpec', 'id'), coalesce(max(id), 0)+1 , false) FROM pushSpec;

alter table device rename to olddevice;
alter table olddevice add column sms text NOT NULL default '', add column email text NOT NULL default '';

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
insert into notify(id, personId, email, sms, phoneToken, padToken, activeEmail, activeSms, activePush, createAt, updateAt) select id, personId, email, sms, token, '', true, true, true, createAt, updateAt from olddevice;
SELECT setval(pg_get_serial_sequence('notify', 'id'), coalesce(max(id), 0)+1 , false) FROM notify;
drop table if exists olddevice;

update notify set sms=milkyway.person.phone, email=milkyway.person.email from milkyway.person where personId=milkyway.person.id;
