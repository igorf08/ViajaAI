-- Migration responsável pela criação da tabela place_item com suas respectivas colunas.
CREATE TABLE tb_placeitem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria CATEGORIA(100) NOT NULL,
    dataViagem DATE NOT NULL;
);