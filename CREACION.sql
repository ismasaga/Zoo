DROP TABLE IF EXISTS avisosAreas;
DROP TABLE IF EXISTS avisosXaulas;
DROP TABLE IF EXISTS avisosanimais;
DROP TABLE IF EXISTS comer;
DROP TABLE IF EXISTS comidas;
DROP TABLE IF EXISTS animais;
DROP TABLE IF EXISTS xaulas;
DROP TABLE IF EXISTS areas;
DROP TABLE IF EXISTS coidadores;
DROP TABLE IF EXISTS contables;
DROP FUNCTION IF EXISTS checkcoidadores(char(10));
DROP FUNCTION IF EXISTS checkcontables(char(10));

CREATE TABLE coidadores (
dni char(9) not null,
pass varchar(50) not null,
nome varchar(100) not null,
telefono varchar(30) not null,
email varchar(50),
PRIMARY KEY (dni)
);

CREATE TABLE contables (
dni char(9) not null,
pass varchar(50) not null,
nome varchar(100) not null,
telefono varchar(30) not null,
email varchar(50),
PRIMARY KEY (dni)
);

CREATE FUNCTION checkContables(_dni char(9))
RETURNS BIGINT AS
$$
   SELECT count(*) FROM contables WHERE dni = _dni
$$ LANGUAGE SQL IMMUTABLE;

ALTER TABLE coidadores
  ADD CONSTRAINT comprobacion
  CHECK(checkContables(dni) = 0);

CREATE FUNCTION checkCoidadores(_dni char(9))
RETURNS BIGINT AS
$$
   SELECT count(*) FROM coidadores WHERE dni = _dni
$$ LANGUAGE SQL IMMUTABLE;

ALTER TABLE contables
  ADD CONSTRAINT comprobacion
  CHECK(checkCoidadores(dni) = 0);

CREATE TABLE areas (
id integer not null,
climatizacion varchar(50),
PRIMARY KEY (id)
);

CREATE TABLE xaulas (
id integer not null,
idArea integer not null,
PRIMARY KEY (id, idArea),
FOREIGN KEY (idArea) REFERENCES areas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE animais (
id integer not null,
nome varchar(50) not null,
especie varchar(50) not null,
edad integer not null,
peso integer not null,
sexo varchar(10) not null,
idXaula integer not null,
idArea integer not null,
idCoidador char(9),
PRIMARY KEY (id),
FOREIGN KEY (idXaula, idArea) REFERENCES xaulas(id, idArea) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (idCoidador) REFERENCES coidadores(dni) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE comidas (
id integer not null,
nome varchar(50) not null,
stock numeric not null,
unidades varchar(10) not null,
PRIMARY KEY (id)
);

CREATE TABLE comer (
cantidadeRacion numeric not null,
animal integer not null,
comida integer not null,
PRIMARY KEY (animal, comida),
FOREIGN KEY (animal) references animais(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (comida) references comidas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE avisosAnimais (
animal integer not null,
nome varchar(50) not null,
descripcion varchar(300) not null,
dataInicio date not null,
dataFin date,
coidador char(9) not null,
contable char(9),
tratamento varchar(500),
PRIMARY KEY (nome, dataInicio, animal),
FOREIGN KEY (animal) references animais(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (coidador) references coidadores(dni) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (contable) references contables(dni) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE avisosAreas (
area integer not null,
nome varchar(50) not null,
descripcion varchar(2000) not null,
dataInicio date not null,
dataFin date,
coidador char(9) not null,
contable char(9),
tratamento varchar(500),
PRIMARY KEY (area, nome, dataInicio),
FOREIGN KEY (area) references areas(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (coidador) references coidadores(dni) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (contable) references contables(dni) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE avisosXaulas (
xaula integer not null,
area integer not null,
nome varchar(50) not null,
descripcion varchar(2000) not null,
dataInicio date not null,
dataFin date,
coidador char(9) not null,
contable char(9),
tratamento varchar(500),
PRIMARY KEY (area, xaula, nome, dataInicio),
FOREIGN KEY (xaula, area) references xaulas(id, idArea) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (coidador) references coidadores(dni) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (contable) references contables(dni) ON DELETE RESTRICT ON UPDATE CASCADE
);