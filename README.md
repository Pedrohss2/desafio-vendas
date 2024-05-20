
# API - Sistema de tarefas

Uma API que permite criar um vendedor, posta venda e gera um resumo de vendas.


End point para criar Vendedo

## Funcionalidades

- CRUD vendedor
- CRUD venda
- Geração de resumo de vendas diarias

## Uso/Exemplos
End-point para criar um vendedor
```METODO: POST
http://localhost:8080/vendedor
```

```Body (raw)
{
    "nome": "teste teste",
    "email":"teste@gmail.com"
}
```
-------------------

End-point: Criar venda

```METODo: POST
http://localhost:8080/vendedor
```

```Body (raw)
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
```METODO: GET
http://localhost:8080/venda/resumo?dataInicio=2023-08-03&dataFim=2023-08-10
```

```Body (raw)
{
    "valor": 100.0,
    "vendedor_id": {
        "id": 1
    },
    "dataVenda": "2023-09-10"
}

-------------------
[
    {
        "nome": "teste teste",
        "totalDeVendas": 1.0,
        "mediDeVendasDiarias": 0.125
    }
]
```
