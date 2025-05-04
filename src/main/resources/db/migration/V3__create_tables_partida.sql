CREATE TABLE partida (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    campeonato_id BIGINT NOT NULL,
    clube_id BIGINT NOT NULL,
    adversario_id BIGINT NOT NULL,
    em_casa BOOLEAN NOT NULL,
    gols_clube INT NOT NULL,
    gols_adversario INT NOT NULL,
    teve_penaltis BOOLEAN,
    gols_penaltis_clube INT,
    gols_penaltis_adversario INT,
    resultado VARCHAR(10),
    CONSTRAINT fk_partida_campeonato FOREIGN KEY (campeonato_id) REFERENCES campeonato(id),
    CONSTRAINT fk_partida_clube FOREIGN KEY (clube_id) REFERENCES clube(id),
    CONSTRAINT fk_partida_adversario FOREIGN KEY (adversario_id) REFERENCES clube(id)
);