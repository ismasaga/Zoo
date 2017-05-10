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
id numeric not null,
climatizacion varchar(50),
PRIMARY KEY (id)
);

CREATE TABLE xaulas (
id numeric not null,
idArea numeric not null,
PRIMARY KEY (id, idArea),
FOREIGN KEY (idArea) REFERENCES areas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE animais (
id numeric not null,
nome varchar(50) not null,
especie varchar(50) not null,
edad integer not null,
peso integer not null,
sexo varchar(10) not null,
idXaula numeric not null,
idArea numeric not null,
idCoidador char(9),
PRIMARY KEY (id),
FOREIGN KEY (idXaula, idArea) REFERENCES xaulas(id, idArea) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (idCoidador) REFERENCES coidadores(dni) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE comidas (
id numeric not null,
nome varchar(50) not null,
stock numeric not null,
unidades varchar(10) not null,
PRIMARY KEY (id)
);

CREATE TABLE comer (
cantidadeRacion numeric not null,
animal numeric not null,
comida numeric not null,
PRIMARY KEY (animal, comida),
FOREIGN KEY (animal) references animais(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (comida) references comidas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE avisosAnimais (
animal numeric not null,
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
area numeric not null,
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
xaula numeric not null,
area numeric not null,
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

INSERT INTO contables (dni, nome, pass, telefono) VALUES ('12345678A', 'Miguel', 'pass', '612121212');
INSERT INTO contables (dni, nome, pass, telefono) VALUES ('23456789C', 'Manolo', 'pass', '234782877');
INSERT INTO coidadores (dni, nome, pass, telefono, email) VALUES ('12345678B', 'Isma', 'pass', '634343434', 'email@example.com');

INSERT INTO Areas (id, climatizacion) VALUES ('00001','Tropical');
INSERT INTO Areas (id, climatizacion) VALUES ('00002','Polar');
INSERT INTO Areas (id, climatizacion) VALUES ('00003','Desertica');
INSERT INTO Areas (id, climatizacion) VALUES ('00004','Acuatica');

INSERT INTO XAULAS (id, idArea) VALUES ('00001','00001');
INSERT INTO XAULAS (id, idArea) VALUES ('00002','00001');
INSERT INTO XAULAS (id, idArea) VALUES ('00003','00001');
INSERT INTO XAULAS (id, idArea) VALUES ('00004','00001');
INSERT INTO XAULAS (id, idArea) VALUES ('00001','00002');
INSERT INTO XAULAS (id, idArea) VALUES ('00002','00002');
INSERT INTO XAULAS (id, idArea) VALUES ('00001','00003');
INSERT INTO XAULAS (id, idArea) VALUES ('00001','00004');
INSERT INTO XAULAS (id, idArea) VALUES ('00002','00004');

INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00001', 'Simba', 'Leon', 5, 80, 'Macho', '00001','00001', '12345678B');
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00005', 'Mufasa', 'Leon', 20, 100, 'Macho', '00001','00001', '12345678B');
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00002', 'Baloo', 'Oso', 7, 80, 'Macho', '00002','00001', '12345678B');
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00003', 'Timon', 'Suricato', 5, 10, 'Macho', '00003','00001', '12345678B');
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00004', 'Pumba', 'Jabalí', 5, 55, 'Macho', '00004','00001', '12345678B');


INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00001','Carne de res','5','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00002','Carne de porco','12','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00003','Calabaza','15','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00004','Fruta','50','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00005','Avena','30','Kilos');

INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('1','1','1');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('1.5','1','2');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('2','2','1');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('3','2','2');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('2','2','4');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('0.5','3','5');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('3','4','3');

INSERT INTO avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001','Arbore rota', 'Arbore partida polo vento na entrada da área', '2017-05-12', null, '12345678B', null, null);
INSERT INTO avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001','Papeleira rota', 'Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah', '2017-03-01', null, '12345678B', null, null);
INSERT INTO avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001','Fai falta pintura', 'Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah', '2017-02-28', null, '12345678B', null, null);
INSERT INTO avisosanimais (animal, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001', 'Pata traseira dereita rota','Rompeuse a pata pelexando contra un elefante mutante','2017-05-30', null, '12345678B', null, null);
