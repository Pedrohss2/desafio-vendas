
# API - Sistema de VENDAS

Uma API que permite criar um vendedor, posta venda e gera um resumo de vendas.

Estrutura do projeto: 
```
desafio-springboot/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com/
|   |   |   |   |-- desafiospringboot/
|   |   |   |   |   |-- desafio/
|   |   |   |   |   |   |-- config/
|   |   |   |   |   |   |-- controller/
|   |   |   |   |   |   |-- dto/
|   |   |   |   |   |   |-- exception/
|   |   |   |   |   |   |-- model/
|   |   |   |   |   |   |-- repository/
|   |   |   |   |   |   |-- service/
|   |   |   |   |   |   |-- DesafioSpringbootApplication.java
|   |   |-- resources/
|   |   |   |-- application.properties
|   |   |-- static/
|   |   |-- templates/
````
## Como rodar o projeto

Clone o projeto na branch 'main'

```git
  git clone https://github.com/Pedrohss2/desafio-vendas.git
```
Abra a pasta que foi clonada no intelij e rode o comando docker compose up para iniciar o container do mysql no terminal.

Após rodar o projeto acesse o mysql para ter acesso as tabelas:
os dados do mysql estão no arquivo `pom.xml`

Para ter acesso ao swagger-API:

```swagger
  http://{host}/swagger-ui/index.html#/blog-controller/create_1
```

## Funcionalidades

- CRUD vendedor
- CRUD venda
- Geração de resumo de vendas diarias

## Uso/Exemplos
End-point para criar um novo vendedor 

Method: POST
```
http://localhost:8080/vendedor
```
Body (raw)
```json
{
    "nome": "teste teste",
    "email":"teste@gmail.com"
}
```
-------------------

End-point: Procurar Vendedor por ID

Method: GET
```
localhost:8080/vendedor/1
```
Body (Pretty)

```json
{
    "id": 1,
    "nome": "teste teste",
    "email": "teste@gmail.com",
    "criadoEm": "2024-05-20T23:17:50.295+00:00",
    "atualizadoEm": "2024-05-20T23:17:50.295+00:00"
}
```
-------------------

End-point: Criar venda

Method: POST

```
http://localhost:8080/venda
```
Body (raw)
```json
{
    "valor": 100.0,
    "vendedor_id": {
        "id": 1
    },
    "dataVenda": "2023-09-10"
}
```
-------------------
End-point: Gerar resumo de vendas

Method: GET
```
http://localhost:8080/venda/resumo?dataInicio=2023-08-03&dataFim=2023-08-10
```
Body (raw)
```json
[
    {
        "nome": "teste teste",
        "totalDeVendas": 1.0,
        "mediDeVendasDiarias": 0.125
    },
    {
        "nome": "teste1 teste1",
        "totalDeVendas": 2.0,
        "mediDeVendasDiarias": 1.5
    }
]
```


## Melhorias
Aceito opniões para melhoria de código, sinta-se avontade em entrar em contato comigo e falar sobre suas ideias

## Autor
- [@pedroH](https://github.com/Pedrohss2)



