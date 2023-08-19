# Gerenciador de Biblioteca

Este é um sistema de gerenciamento de biblioteca construído com Spring Boot.

## Funcionalidades

- Gerenciamento de usuários: adicionar, editar e remover usuários.
- Gerenciamento de livros: adicionar, editar e remover livros.
- Empréstimo de livros: os usuários podem emprestar até dois livros por vez.
- Devolução de livros: os usuários podem devolver os livros emprestados.

## Entidades

O sistema possui três entidades principais: `Usuário`, `Livro` e `Empréstimo`.

### Usuário

A entidade `Usuario` representa um usuário do sistema. Possui os seguintes campos:
- `id`: identificador único do usuário.
- `nome`: nome do usuário.
- `email`: endereço de email do usuário.
- `telefone`: telefone do usuário.

### Livro

A entidade `Livro` representa um livro no sistema. Possui os seguintes campos:
- `id`: identificador único do livro.
- `autor`: autor do livro.
- `titulo`: título do livro.

### Empréstimo

A entidade `Empréstimo` representa um empréstimo de livro. Possui os seguintes campos:
- `id`: identificador único do empréstimo.
- `livro_id`: identificador do livro emprestado.
- `usuario_id`: identificador do usuário que pegou o livro emprestado.
- `dataEmprestimo`: data do empréstimo.
- `dataDevolucao`: data da devolução.

## Chamadas do Serviço

O sistema possui as seguintes chamadas de serviço disponíveis:

### Livros
- `GET /livros`: busca todos os livros.
- `GET /livros?pesquisa=`: busca livros por título ou autor.
- `GET /livros/{id}`: busca livro por id.
- `POST /livros`: adiciona livro.
- `PUT /livros/{id}`: modifica livro.
- `DELETE /livros/{id}`: remove livro.

### Usuários
- `GET /usuarios`: busca todos os usuários.
- `GET /usuarios?pesquisa=`: busca usuários por nome ou email.
- `GET /usuarios/{id}`: busca usuário por id.
- `POST /usuarios`: adiciona usuário.
- `PUT /usuarios/{id}`: modifica usuário.
- `DELETE /usuarios/{id}`: remove usuário.

### Empréstimos
- `GET /emprestimos`: busca todos os empréstimos.
- `GET /emprestimos?pesquisa=`: busca empréstimos por usuário ou livro.
- `GET /emprestimos/{id}`: busca empréstimo por id.
- `GET /emprestimos/devolvidos`: busca empréstimos já devolvidos.
- `GET /emprestimos/nao-devolvidos`: busca empréstimos não devolvidos.
- `POST /emprestimos`: adiciona empréstimo.
- `PUT /emprestimos/{id}`: devolve livro.

## Guia de Execução

Este guia fornece instruções passo a passo para executar o projeto.

### Pré-requisitos

Antes de começar, certifique-se de que o JDK e o Maven estão instalados e que as variáveis de ambiente estão corretamente definidas.

### Configuração

1. Clone o repositório `git clone https://github.com/erickgnclvs/gerenciador-biblioteca.git`.
2. Abra o arquivo `application.properties` e configure-o para usar o MySQL ou o H2.
3. Se estiver usando o MySQL, configure o usuário e a senha do banco de dados e crie um banco de dados chamado `biblioteca`.

### Compilação

Na pasta raiz do projeto, execute o seguinte comando para compilar o projeto:

```
mvn package
```

### Execução

Para executar o projeto, use o seguinte comando na pasta raiz do projeto:

```
mvn spring-boot:run
```

Realize testes através dos end-points em http://localhost:8080/api/v1.

## Tecnologias utilizadas

- IntelliJ Ultimate Edition - versão free para estudantes
- Spring Boot 3.1.2
- Maven 3.9.4
- Java Development Kit 17.0.8
- MySQL Community Server 8.0.33

<br><br>

````
              ******       ******
            **********   **********
          ************* *************
         *****************************
         *****************************
         ******** me contrata ********
          ***************************
            ***********************
              *******************
                ***************
                  ***********
                    *******
                      ***
                       *