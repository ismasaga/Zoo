INSERT INTO contables (dni, nome, pass, telefono) VALUES ('12345678A', 'Miguel', 'pass', '612121212');
INSERT INTO contables (dni, nome, pass, telefono) VALUES ('23456789C', 'Manolo', 'pass', '234782877');
INSERT INTO coidadores (dni, nome, pass, telefono, email) VALUES ('12345678B', 'Isma', 'pass', '634343434', 'email@example.com');
INSERT INTO coidadores (dni, nome, pass, telefono, email) VALUES ('23456789B', 'Erundino', 'pass', '634343434', 'email@example.com');

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
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00006', 'Lolo', 'Oso', 10, 250, 'Macho', '00001','00002', '23456789B');
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00007', 'Lila', 'Lobo', 3, 25, 'Femia', '00001','00003', '23456789B');
INSERT INTO animais (id, nome, especie, edad, peso, sexo, idXaula, idArea, idCoidador) VALUES ('00008', 'Bicho', 'Lince', 5, 25, 'Macho', '00001','00002', '12345678B');


INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00001','Carne de res','5','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00002','Carne de porco','12','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00003','Calabaza','15','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00004','Fruta','50','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00005','Avena','30','Kilos');
INSERT INTO comidas (id,nome,stock,unidades) VALUES ('00006','Pescado','10','Kilos');

INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('1','1','1');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('2','1','2');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('2','5','1');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('3','5','2');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('2','2','4');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('1','3','5');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('3','4','3');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('3','6','6');
INSERT INTO comer (cantidadeRacion, animal, comida) VALUES ('1','7','1');

INSERT INTO avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001','Arbore rota', 'Arbore partida polo vento na entrada da área', '2017-05-12', null, '12345678B', null, null);
INSERT INTO avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001','Papeleira rota', 'Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah', '2017-03-01', null, '12345678B', null, null);
INSERT INTO avisosareas (area, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001','Fai falta pintura', 'Blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah', '2017-02-28', null, '12345678B', null, null);
INSERT INTO avisosanimais (animal, nome, descripcion, dataInicio, dataFin, coidador, contable, tratamento) VALUES ('00001', 'Pata traseira dereita rota','Rompeuse a pata pelexando contra un elefante mutante','2017-05-30', null, '12345678B', null, null);