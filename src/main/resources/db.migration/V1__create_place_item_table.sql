-- Migration responsável pela criação da tabela place_item com suas respectivas colunas.
CREATE TABLE tb_placeitem (
    nome varchar(128);
    categoria varchar(128);
    dataViagem date;
);