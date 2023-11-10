CREATE TABLE Curso (
    Codigo int NOT NULL AUTO_INCREMENT,
    Nome varchar(100) NOT NULL,
    Descricao varchar(4000) NOT NULL,
    Duracao int NOT NULL,
    PRIMARY KEY (Codigo)
);

CREATE TABLE Funcionario(
    Codigo int NOT NULL AUTO_INCREMENT,
    Nome varchar(200) NOT NULL,
    CPF char(11) NOT NULL,
    Nascimento DATE NOT NULL,
    Cargo varchar(200) NOT NULL,
    Admissao DATE NOT NULL,
    Status_funcionario BIT NOT NULL,
    PRIMARY KEY (Codigo)
);

CREATE TABLE Turma(
    Codigo int NOT NULL AUTO_INCREMENT,
    Inicio DATE NOT NULL,
    Fim DATE NOT NULL,
    Local_treinamento varchar(200) NOT NULL,
    Curso int NOT NULL,
    PRIMARY KEY (Codigo),
    FOREIGN KEY (Curso) REFERENCES Curso(Codigo)
);

CREATE TABLE TurmaParticipante(
    Codigo int NOT NULL AUTO_INCREMENT,
    Turma int NOT NULL,
    Funcionario int NOT NULL,
    PRIMARY KEY (Codigo),
    FOREIGN KEY (Turma) REFERENCES Turma(Codigo),
    FOREIGN KEY (Funcionario) REFERENCES Funcionario(Codigo)
);