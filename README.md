# Gerenciador de Biblioteca

Este é um sistema de gerenciamento de biblioteca construído com Spring Boot.

## Funcionalidades

- Gerenciamento de usuários: adicionar, editar e remover usuários.
- Gerenciamento de livros: adicionar, editar e remover livros.
- Empréstimo de livros: os usuários podem pegar até dois livros emprestados por vez.
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
- `GET /api/v1/livros`: busca todos os livros.
- `GET /api/v1/livros?pesquisa=`: busca livros por título ou autor.
- `GET /api/v1/livros/{id}`: busca livro por id.
- `POST /api/v1/livros`: adiciona livro.
- `PUT /api/v1/livros/{id}`: modifica livro.
- `DELETE /api/v1/livros/{id}`: remove livro.
- `GET /api/v1/livros/{id}/emprestimos`: busca empréstimos do livro.
- `GET /api/v1/livros/{id}/emprestimos/ativo`: busca empréstimo ativo do livro.
- `GET /api/v1/livros/{id}/emprestimos/devolvidos`: busca empréstimos devolvidos do livro.

### Usuários
- `GET /api/v1/usuarios`: busca todos os usuários.
- `GET /api/v1/usuarios?pesquisa=`: busca usuários por nome ou email.
- `GET /api/v1/usuarios/{id}`: busca usuário por id.
- `POST /api/v1/usuarios`: adiciona usuário.
- `PUT /api/v1/usuarios/{id}`: modifica usuário.
- `DELETE /api/v1/usuarios/{id}`: remove usuário.
- `GET /api/v1/usuarios/{id}/emprestimos`: busca empréstimos do usuário.
- `GET /api/v1/usuarios/{id}/emprestimos/ativos`: busca empréstimos ativos do usuário.
- `GET /api/v1/usuarios/{id}/emprestimos/devolvidos`: busca empréstimos devolvidos do usuário.

### Empréstimos
- `GET /api/v1/emprestimos`: busca todos os empréstimos.
- `GET /api/v1/emprestimos?pesquisa=`: busca empréstimos por usuário ou livro.
- `GET /api/v1/emprestimos/{id}`: busca empréstimo por id.
- `GET /api/v1/emprestimos/devolvidos`: busca empréstimos já devolvidos.
- `GET /api/v1/emprestimos/ativos`: busca empréstimos não devolvidos.
- `POST /api/v1/emprestimos`: adiciona empréstimo.
- `PATCH /api/v1/emprestimos/{id}`: devolve livro.

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

### Testes

Importe o arquivo `biblioteca.postman_collection.json` como uma collection no Postman e realize testes através dos end-points em http://localhost:8080/api/v1, modificando os parâmetros necessários para testar todos os casos.

## Tecnologias utilizadas

- IntelliJ Ultimate Edition - versão free para estudantes
- Spring Boot 3.1.2
- Maven 3.9.4
- Java Development Kit 17.0.8
- MySQL Community Server 8.0.33
- Postman Agent 0.4.15

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
