create table cupom(
id Serial PRIMARY KEY,
codigo varchar(20) UNIQUE,
desconto decimal(8,3),
exp_date varchar(100) 
);

SELECT * FROM cupom

DROP TABLE cupom
