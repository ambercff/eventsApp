CREATE TABLE user (
    id_user BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    senha VARCHAR(200) NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    ativo TINYINT NOT NULL
);

CREATE TABLE evento (
    id_evento BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL,
    data DATETIME NOT NULL,
    local VARCHAR(200) NOT NULL,
    capacidade_maxima INT NOT NULL
);

CREATE TABLE evento_organizadores (
    id_evento BIGINT NOT NULL,
    id_user BIGINT NOT NULL,
    PRIMARY KEY (id_evento, id_user),
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE
);

CREATE TABLE inscricao (
    id_inscricao BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_participante BIGINT NOT NULL,
    id_evento BIGINT NOT NULL,
    status_inscricao VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_participante) REFERENCES user(id_user) ON DELETE CASCADE,
    FOREIGN KEY (id_evento) REFERENCES evento(id_evento) ON DELETE CASCADE
);
