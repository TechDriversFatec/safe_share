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
- Criação do burndown (é uma representação gráfica do trabalho a ser feito versus tempo).
- Criação do README.md

### Sprint 2
- Estrutura do Banco de Dados da aplicação.
    -   [Modelo](modelo.sql) físico do Banco de dados utilizando o SGBD PostgreSql.
       
- Back-end inicial para simulação de dados.
    - O projeto foi iniciado com Spring Boot, com os módulos de persistência JPA (para persistir objetos Java), Lombok(biblioteca Java focada em produtividade e redução de código). E o compilador Maven. Mais detalhes da configuração inicial do projeto pode ser vista em [Pom](pom.xml).
    - Diagrama de Classe.
    - Criação das entidades bem como os endpoints.
    - Adição do projeto no Swagger (que é um framework para descrição, consumo e visualização de serviços RESTful, bem como a documentação da implementação).
    - Deploy da [API](https://safe-share-si.herokuapp.com/swagger-ui.html#/).  
    

### Sprint 3
- Implementação de criptografia de dados, com a finalidade de ofuscar dados pessoais.
    - Geração de chaves e códigos de autenticação utilizando o Java Cryptography Extension ou JCE.
- Implementar regra de compartilhamento visando dados estatística (dados sem link com o titular)
    - Inserção de alguns [dados](https://github.com/RodrigoMarcelin/safe_share/blob/homologacao/src/main/resources/data.sql) fictícios no Banco de Dados para testes.
    - Foi criado um [trigger](trigger.sql) no Banco de dados para sempre que o titular modificar o campo se deseja ou não compartilhar os seus dados seja criado um log com data e campo modificado.
    - Inicializado algumas especificações do front-end.

### Sprint 4
- Implementação de uma regra de negócio, onde o compartilhamento de dados pessoais, só aconteça com prévia autorização do titular, isso por solicitação de compartilhamento, deixando claro os fins para tratamento desses dados.
    - Afim de melhorar o gerenciamento de Log's da aplicação, foi implementado o LoggerFactory, com o intuito de deixar mais consistente sem possibilidade de manipulação dos dados.
    - Criação de uma tabela para armazenar o id do usuário e a chave gerada aleatóriamente.
    - Implementação do select.
    - Funcionalidades do front-end.


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



