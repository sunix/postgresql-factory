CREATE TABLE public.test_users
(
  id serial NOT NULL,
  userName text NOT NULL,
  firstName text NOT NULL,
  lastName text NOT NULL,
  birthday date NOT NULL,
  email text NOT NULL,
  CONSTRAINT "idPKey" PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
)
;