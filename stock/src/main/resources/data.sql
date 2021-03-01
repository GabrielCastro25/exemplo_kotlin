DROP TABLE IF EXISTS tb_reserva;
DROP TABLE IF EXISTS tb_estoque;

CREATE TABLE tb_reserva (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  id_pedido INT NOT NULL,
  id_produto INT NOT NULL,
  qt_itens INT NOT NULL
);

CREATE TABLE tb_estoque (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  id_produto INT NOT NULL,
  qt_disponivel INT NOT NULL
);

insert into tb_estoque (id_produto, qt_disponivel) values (20, 50);
insert into tb_estoque (id_produto, qt_disponivel) values (21, 10);
insert into tb_estoque (id_produto, qt_disponivel) values (22, 5);
commit;