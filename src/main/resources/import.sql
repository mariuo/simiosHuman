CREATE TABLE IF NOT EXISTS tb_dna (id int8 generated by default as identity, dna_type int4, sequence TEXT, primary key (id));

INSERT into tb_dna (ID, DNA_TYPE, SEQUENCE) VALUES (1, 1, 'CTGAGA CTATGA TATTGA AGAGGA CCCCTA TGAAAA');
