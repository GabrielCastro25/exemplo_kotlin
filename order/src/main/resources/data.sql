DROP TABLE IF EXISTS tb_pedido;

CREATE TABLE tb_pedido (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  id_produto INT NOT NULL,
  vl_total NUMBER(14,2) NOT NULL,
  qt_itens INT NOT NULL,
  ds_status VARCHAR(300)
);
