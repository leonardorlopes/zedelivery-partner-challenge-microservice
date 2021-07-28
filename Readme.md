# Zé Delivery - BackEnd Challenge (Partners)

By Leonardo Rodrigues Lopes de Souza - leonardorodrigues153@gmail.com

Aqui você encontra a solução proposta para o case técnico da Zé Delivery disponível no link: 
https://github.com/ZXVentures/ze-code-challenges/blob/master/backend.md

## Premissas

Disponibilizar uma API REST que implemente algumas funcionalidades para incluir/obter dados dos parceiros Zé Delivery, bem como sua localização.
  - O campo `addres` segue o formato GeoJSON Point
  - O campo `coverageArea` segue o formato GeoJSON MultiPolygon
  - O campo `document` deve ser único entre os parceiros
  - O campo `id` deve ser único entre os parceiros

## Pré-requisitos

Aqui estão os requisitos e insumos necessários para a execução da aplicação:
- Docker (https://www.docker.com/products/docker-desktop)
  * Para utilizar o Docker deve ser habilitada a virtualização no Windows.
- Java (https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- PostgreSql 11 (https://www.enterprisedb.com/postgresql-tutorial-resources-training?cid=55)
- IDE Eclipse para testes locais (https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2021-06/R/eclipse-inst-jre-win64.exe)

## Execução

Para executar o projeto e suas dependências, foi utilizado o Docker, visando facilitar a execução da aplicação

Para executar:
  Navegar até a raiz do projeto via terminal(de sua preferência)
  Executar o comando `docker-compose up`

## Execução Local

Para executar localmente o projeto, abra o mesmo na IDE
Inicie o PgAdmin
As dependências do projeto devem ser baixadas pelo Maven
Executar como SpringBootApp ou Compilar com o Maven e executar o jar diretamente via terminal.

### Aplicação(zedelivery-partner-challenge-microservice)

Porta: http://localhost:8889/
Documentação: http://localhost:8889/swagger-ui.html

### Banco de dados 
Banco de dados: partners
Porta: 5432
Usuário: postgres
Senha: admin

## API Rest - Recursos

### Obter todos os parceiros
![image](https://user-images.githubusercontent.com/55765004/127349728-ddaec479-1673-4bfb-a20d-07fb41741a32.png)

`curl -X GET "http://localhost:8889/v1/partner" -H "accept: */*"`

### Obter parceiro pelo id
![image](https://user-images.githubusercontent.com/55765004/127349907-a2b54fe7-c9f8-47b9-8f32-f630d9fd0743.png)

`curl -X GET "http://localhost:8889/v1/partner/id/1" -H "accept: */*"`

### Obter parceiro mais próximo através das coordenadas
![image](https://user-images.githubusercontent.com/55765004/127350034-846450f3-83e1-4035-bb64-6827f235b3d5.png)

`curl -X GET "http://localhost:8889/v1/partner/latitude/-23.013538/longitude/%20-43.297337" -H "accept: */*"`

### Inserir um novo parceiro
![image](https://user-images.githubusercontent.com/55765004/127350327-98d608ef-df50-4cd5-962b-8b2801854c0d.png)

`curl -X POST "http://localhost:8889/v1/partner" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\": \"3\", \"tradingName\": \"Adega Sao Paulo\", \"ownerName\": \"Pedro Silva\", \"document\": \"04666182390\", \"coverageArea\": { \"type\": \"MultiPolygon\", \"coordinates\": [ [ [ [ -38.6577, -3.7753 ], [ -38.63212, -3.81418 ], [ -38.61925, -3.82873 ], [ -38.59762, -3.84004 ], [ -38.58727, -3.84345 ], [ -38.58189, -3.8442 ], [ -38.57667, -3.84573 ], [ -38.56706, -3.85015 ], [ -38.56637, -3.84937 ], [ -38.56268, -3.84286 ], [ -38.56148, -3.83772 ], [ -38.55881, -3.82411 ], [ -38.55577, -3.81507 ], [ -38.55258, -3.80674 ], [ -38.54968, -3.80222 ], [ -38.53406, -3.79495 ], [ -38.52894, -3.77718 ], [ -38.52517, -3.76313 ], [ -38.53118, -3.76203 ], [ -38.53968, -3.76126 ], [ -38.54577, -3.76151 ], [ -38.55344, -3.76102 ], [ -38.56327, -3.76029 ], [ -38.58118, -3.75907 ], [ -38.60079, -3.75423 ], [ -38.60671, -3.74772 ], [ -38.61787, -3.7431 ], [ -38.62577, -3.7472 ], [ -38.63332, -3.7496 ], [ -38.65049, -3.76057 ], [ -38.6577, -3.7753 ] ] ] ] }, \"address\": { \"type\": \"Point\", \"coordinates\": [ -38.59826, -3.774186 ] } }"`















