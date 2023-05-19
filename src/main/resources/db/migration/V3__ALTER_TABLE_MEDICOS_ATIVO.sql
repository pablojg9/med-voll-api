ALTER TABLE medicos ADD COLUMN ATIVO tinyint;
update medicos set ativo = 1;
