# Projeto Segurança da informação

## Integrantes da equipe
- Ariana Rodrigues Cursino [github](https://github.com/arcursino)/[linkedin](https://www.linkedin.com/in/arcursino/) 
- Felipe Augusto Carolino [github]()/[linkedin]()
- Gilherme De Polli Migliano [github]( https://github.com/guilhermemigliano)/[linkedin](https://www.linkedin.com/in/guilhermemigliano)
- Matheus da Cruz Oliveira dos Santos [github](https://github.com/matheuscosantos)/[linkedin](https://www.linkedin.com/in/matheuscosantos/)
- Rodrigo Marcelino Silva Amorim [github](https://github.com/RodrigoMarcelin)/[linkedin]()

### Projeto safe_share
Usaremos como prova de conceito: 
- Criação de banco de dados para uma loja de departamentos.
- Alteração no banco de dados para atender a geração de relatórios e ofuscação dos dados.
- Criação de uma interface via API Rest para que o usuário possa fazer o pedido das mudanças.

## Problema
O problema que iremos tratar nesse projeto, é sobre compartilhamento de dados pessoais.

## Objetivo
Aplicação do artigo 9º, da lei LGPD no Back-end.
Com isso iremos trabalhar com:
- Ofuscar dados pessoais, para que dados de vendas sejam fornecidos de forma estatística, assim não tornando o portador do dado identificável.
- Criar regra de negócio, para que caso haja uma solicitação de dados pessoais, o titular do dado tenha que ser consultado para autorização.
- Criar uma aplicação Front-end, onde o titular do dado tenha acesso direto ao histórico de compartilhamento, saiba a finalidade específica do tratamento, tenha acesso a pessoa responsável pelo controle de dados, e de forma clara consiga autorizar ou negar a utilização deles quando solicitado.

Para mais informações sobre a lei e tópicos deste trabalho, acessar o arquivo [Info.md :book:](/Info.md) 


## Desenvolvimento
**Para alcançar o objetivo, foram utilizadas as seguintes estruturas:**


Dado o contexto acima, desenvolveremos uma aplicação com um CRUD com acesso a Bancos de dados voltada a resolver problemas de anonimização de uma aplicação de vendas online fictícia.

Para isto contaremos com a seguinte estrutura:

- Estrutura do Sistema:

![Estrutura](/images/estrutura.png)

**Técnicas utilizadas para Criptografar**
Dada a resolução do problema, decidimos implementar um algoritmo  de criptografia simétrica simples que resolve a questão do armazenamento seguro de informações pessoais de forma anonimizada. Ao se cadastrar no site, para cada usuário é criada uma chave simétrica simples e é armazenada dentro de um banco de dados de chaves criptografadas, separado do banco de dados pessoais.

Para isto utilizaremos a criptografia do padrão [AES](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197.pdf) (Advanced Encryption System), utilizando uma senha com um tamanho de 256 bits e o método de criptografia em bloco no modelo [CBC](https://csrc.nist.gov/publications/detail/sp/800-38a/final) (Cypher Block Chaining).

- Arquitetura Criptografia Java - JCA

O JCA é uma peça importante da plataforma e contém uma arquitetura de "provedor" e um conjunto de APIs para assinaturas digitais, resumos de mensagem (hashes), certificados e validação de certificado, criptografia (cifras de bloco / fluxo simétrico / assimétrico), geração de chave e gerenciamento e geração segura de números aleatórios, para citar alguns. Essas APIs permitem que os desenvolvedores integrem facilmente a segurança ao código do aplicativo. A arquitetura foi projetada em torno dos seguintes princípios:

1. Independência de implementação: os aplicativos não precisam implementar algoritmos de segurança. Em vez disso, eles podem solicitar serviços de segurança da plataforma Java. Os serviços de segurança são implementados em provedores, que são conectados à plataforma Java por meio de uma interface padrão. Um aplicativo pode contar com vários provedores independentes para funcionalidade de segurança.

2. Interoperabilidade de implementação: os provedores são interoperáveis ​​entre aplicativos. Especificamente, um aplicativo não está vinculado a um provedor específico e um provedor não está vinculado a um aplicativo específico.

3. Extensibilidade do algoritmo: a plataforma Java inclui vários provedores integrados que implementam um conjunto básico de serviços de segurança amplamente usados ​​atualmente. No entanto, alguns aplicativos podem depender de padrões emergentes ainda não implementados ou de serviços proprietários. 

**Arquitetura dos Provedores**
![Criptografia](/images/crypto.png)
![Instância](/images/crypto_instance.png)


Para mais especificações da [JCA](https://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html) 

## Backlog

O Backlog abaixo demonstra os processos que realizaremos para a criação deste projeto:

### Sprint 1
- Organização do github.
- Criação do burndown (é uma representação gráfica do trabalho a ser feito versus tempo).
- Criação do README.md

### Sprint 2
- Estrutura do Banco de Dados da aplicação.
    -   [Modelo](backend/modelo.sql) físico do Banco de dados utilizando o SGBD PostgreSql.
       
- Back-end inicial para simulação de dados.
    - O projeto foi iniciado com Spring Boot, com os módulos de persistência JPA (para persistir objetos Java), Lombok(biblioteca Java focada em produtividade e redução de código). E o compilador Maven. Mais detalhes da configuração inicial do projeto pode ser vista em [Pom](backend/pom.xml).
    - Diagrama de Classe.
    - Criação das entidades bem como os endpoints.
    - Adição do projeto no Swagger (que é um framework para descrição, consumo e visualização de serviços RESTful, bem como a documentação da implementação).
    - Deploy da [API](https://safe-share-si.herokuapp.com/swagger-ui.html#/).  
    

### Sprint 3
- Implementação de criptografia de dados, com a finalidade de ofuscar dados pessoais.
    - Geração de chaves e códigos de autenticação utilizando o Java Cryptography Extension ou JCE.
- Implementar regra de compartilhamento visando dados estatística (dados sem link com o titular)
    - Inserção de alguns [dados](https://github.com/RodrigoMarcelin/safe_share/blob/homologacao/backend/src/main/resources/data.sql) fictícios no Banco de Dados para testes.
    - Foi criado um [trigger](backend/trigger.sql) no Banco de dados para sempre que o titular modificar o campo se deseja ou não compartilhar os seus dados seja criado um log com data e campo modificado.
    - Inicializado algumas especificações do front-end.

### Sprint 4
- Implementação de uma regra de negócio, onde o compartilhamento de dados pessoais, só aconteça com prévia autorização do titular, isso por solicitação de compartilhamento, deixando claro os fins para tratamento desses dados.
    - Afim de melhorar o gerenciamento de Log's da aplicação, foi implementado o LoggerFactory, com o intuito de deixar mais consistente sem possibilidade de manipulação dos dados.
    - Criação de uma tabela para armazenar o id do usuário e a chave gerada aleatóriamente.
    - Implementação do select.
    - Funcionalidades do front-end.


### Sprint 5
- Desenvolvimento do [Front-end](https://github.com/RodrigoMarcelin/safe_share/tree/master/frontend) para a aplicação
- Inicio implementação do [Front-end](https://github.com/RodrigoMarcelin/safe_share/blob/master/frontend/src/Routes.js) com o Back-end da aplicação

### Sprint 6
- Término da implementação Front-end
- Criação uma apresentação para o trabalho






