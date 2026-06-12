DROP TABLE IF EXISTS pagamento;
DROP TABLE IF EXISTS item_pedido;
DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
telefone VARCHAR(20)
);

CREATE TABLE produto (
id SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
preco DECIMAL(10,2) NOT NULL,
estoque INT NOT NULL
);

CREATE TABLE pedido (
id SERIAL PRIMARY KEY,
id_cliente INT NOT NULL,
data_pedido DATE NOT NULL,
total DECIMAL(10,2) NOT NULL,
status VARCHAR(30) NOT NULL,
FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE item_pedido (
id SERIAL PRIMARY KEY,
id_pedido INT NOT NULL,
id_produto INT NOT NULL,
quantidade INT NOT NULL,
preco_unitario DECIMAL(10,2) NOT NULL,
FOREIGN KEY (id_pedido) REFERENCES pedido(id),
FOREIGN KEY (id_produto) REFERENCES produto(id)
);

CREATE TABLE pagamento (
id SERIAL PRIMARY KEY,
id_pedido INT NOT NULL,
forma_pagamento VARCHAR(30) NOT NULL,
valor DECIMAL(10,2) NOT NULL,
status VARCHAR(30) NOT NULL,
FOREIGN KEY (id_pedido) REFERENCES pedido(id)
);
