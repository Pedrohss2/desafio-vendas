
# API - Sistema de VENDAS

Uma API que permite criar um vendedor, posta venda e gera um resumo de vendas.

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
```
{
    "nome": "teste teste",
    "email":"teste@gmail.com"
}
```
-------------------

End-point: Procurar Vendedor por ID

Method: GET
```
localhost:8080/vendedor/xxxx
```
Body (Pretty)

```
{
    "id": xxxx,
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
http://localhost:8080/vendedor
```
Body (raw)
```
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
```
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


