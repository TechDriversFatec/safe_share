# Projeto Segurança da informação

## Problema
O problema que iremos tratar nesse projeto, é sobre compartilhamento de dados pessoais

## Objetivo
Aplicação do artigo 9º, da lei LGPD no Back-end.
Com isso iremos trabalhar com:
- Ofuscar dados pessoais, para que dados de vendas sejam fornecidos de forma estatística, assim não tornando o portador do dado identificável.
- Criar regra de negócio, para que caso haja uma solicitação de dados pessoais, o titular do dado tenha que ser consultado para autorização.
- Criar uma aplicação Front-end, onde o titular do dado tenha acesso direto ao histórico de compartilhamento, saiba a finalidade específica do tratamento, tenha acesso a pessoa responsável pelo controle de dados, e de forma clara consiga autorizar ou negar a utilização deles quando solicitado.

### Projeto safe_share
Usaremos como prova de conceito: 
- Criação de banco de dados para uma loja de departamentos.
- Alteração no banco de dados para atender a geração de relatórios e ofuscação dos dados.
- Criação de uma interface via API Rest para que o usuário possa fazer o pedido das mudanças.

## O que é LGPD?
[![](http://img.youtube.com/vi/y7SamL2wYSc/0.jpg)](http://www.youtube.com/watch?v=y7SamL2wYSc "O que é LGPD?")

## Art.9º
Art. 9º O titular tem direito ao acesso facilitado às informações sobre o tratamento de seus dados, que deverão ser disponibilizadas de forma clara, adequada e ostensiva acerca de, entre outras características previstas em regulamentação para o atendimento do princípio do livre acesso:

I - finalidade específica do tratamento;

II - forma e duração do tratamento, observados os segredos comercial e industrial;

III - identificação do controlador;

IV - informações de contato do controlador;

V - informações acerca do uso compartilhado de dados pelo controlador e a finalidade;

## Processo de desenvolvimento

### Sprint 1
- Organização do github.
- Criação do burndown.
- Criação do README.md

### Sprint 2
- Estrutura do Banco de Dados da aplicação.
- Back-end inicial para simulação de dados.

### Sprint 3
- Implementação de criptografia de dados, com a finalidade de ofuscar dados pessoais.
- Implementar regra de compartilhamento visando dados estatística (dados sem link com o titular)

### Sprint 4
- Implementação de uma regra de negócio, onde o compartilhamento de dados pessoais, só aconteça com prévia autorização do titular, isso por solicitação de compartilhamento, deixando claro os fins para tratamento desses dados.

### Sprint 5
- Desenvolvimento do Front-end para a aplicação
- Implementação do Front-end com o Back-end da aplicação

### Sprint 6
- Criação uma apresentação para o trabalho

## Integrantes da equipe
- Ariana Rodrigues Cursino
- Felipe Augusto Carolino
- Gilherme De Polli Migliano
- Matheus da Cruz Oliveira dos Santos
- Rodrigo Marcelino Silva Amorim
- Rodrigo Querino Ferreira da Costa



