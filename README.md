# Guia de Execução

Este guia fornece instruções passo a passo para executar o projeto.

## Pré-requisitos

Antes de começar, certifique-se de que o JDK e o Maven estão instalados e que as variáveis de ambiente estão corretamente definidas.

## Configuração

1. Clone o repositório `git clone https://github.com/erickgnclvs/gerenciador-biblioteca.git`.
2. Abra o arquivo `application.properties` e configure-o para usar o MySQL ou o H2.
3. Se estiver usando o MySQL, configure o usuário e a senha do banco de dados e crie um banco de dados chamado `biblioteca`.

## Compilação

Na pasta raiz do projeto, execute o seguinte comando para compilar o projeto:

```
mvn package
```

## Execução

Para executar o projeto, use o seguinte comando na pasta raiz do projeto:

```
java -jar /target/<nomeDoPacoteGerado>
```

Isso iniciará a aplicação. Siga as instruções na tela para usar o programa.

## Continuar instruções de teste...