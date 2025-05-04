CREATE DATABASE microSaaSDB;
USE microSaaSDB;

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(100) NOT NULL UNIQUE,
    endereco VARCHAR(255),
    contato VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Prestador (
    id_prestador INT PRIMARY KEY AUTO_INCREMENT,
    nome_fantasia VARCHAR(100),
    nome_completo VARCHAR(100) NOT NULL,
    foto_perfil VARCHAR(255),
    endereco VARCHAR(255),
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Imagem_Portfolio (
    id_imagem INT PRIMARY KEY AUTO_INCREMENT,
    id_prestador INT NOT NULL,
    caminho_imagem VARCHAR(255),
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador)
);

CREATE TABLE Disponibilidade (
    id_disponibilidade INT PRIMARY KEY AUTO_INCREMENT,
    id_prestador INT NOT NULL,
    dia_semana INT NOT NULL, 
    horario TIME NOT NULL,
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador),
    UNIQUE (id_prestador, dia_semana, horario)
);

DROP TABLE Disponibilidade;

CREATE TABLE Agendamento (
    id_agendamento INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_prestador INT NOT NULL,
    data_agendamento DATE NOT NULL,
    hora_agendamento TIME NOT NULL,
    status ENUM('solicitado', 'aceito', 'concluido') DEFAULT 'solicitado',
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_prestador) REFERENCES Prestador(id_prestador),
    UNIQUE (id_prestador, data_agendamento, hora_agendamento) 
);

CREATE TABLE Log_Status_Agendamento (
    id_log INT PRIMARY KEY AUTO_INCREMENT,
    id_agendamento INT NOT NULL,
    status_antigo VARCHAR(20),
    status_novo VARCHAR(20),
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_agendamento) REFERENCES Agendamento(id_agendamento)
);

SELECT * FROM Cliente;
SELECT * FROM Prestador;
SELECT * FROM Imagem_Portfolio;
SELECT * FROM Disponibilidade;
SHOW CREATE TABLE Prestador;


