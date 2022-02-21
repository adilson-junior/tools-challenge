# ToolsChallenge-API

API que realiza transações de compra por cartão de crédito. 

Para cada tipo de processamento, é necessário enviar os dados da transação no formato JSON, o número do cartão de crédito, descrição da compra e forma de pagamento.

A API verifica se o cartão de crédito já existe no sistema. Caso exista, ela verifica o saldo e, caso o saldo for positivo, realiza o pagamento a ser processado.

A API permite consultar os dados do pagamento por ID e de todos processados.

A API permite realizar o estorno da transação.

# Tecnologias utilizadas
- Java 11
- Spring Boot
- Maven
- Testes unitários com JUnit.

## Arquitetura 
*main:*
1. Domain  
2. Repository
3. Service
4. Controller
5. Enuns
6. Util
7. Exception
8. VO

*test:*
1. Service

## Como executar
Pré-requisitos: Java 11

```bash
# clonar repositório
git clone https://github.com/adilson-junior/tools-challenge.git

# entrar na pasta do projeto back end
cd tools-challenge

# executar o projeto
./mvnw spring-boot:run
```

## Como testar 
O sistema possui os endpoints: 
<br>
**``POST api/pagamentos``**: Envia um requisição para o processamento de pagamento.
<br>
*Exemplo do corpo da requisição para pagamento:*
```bash
{
    "transacao": {
        "cartao": "5387444452528983",
        "descricao": {
            "valor": "100",
            "dataHora": "01/05/2021 18:30:00",
            "estabelecimento": "Pet 100%"            
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": "1"
        }
    }
}
```
*Exemplo do corpo da resposta para pagamento:*
```bash
{
    "transacao": {
        "id": 2,
        "cartao": "************8983",
        "descricao": {
            "valor": 100.0,
            "dataHora": "01/05/2021 18:30:00",
            "estabelecimento": "Pet 100%",
            "nsu": "2",
            "codigoAutorizacao": "285486170",
            "status": "AUTORIZADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": 1
        }
    }
}
```
 **``PUT api/pagamentos/1``**: Envia uma requisição para o processamento de estorno por id informado na URL.
 <br>
*Exemplo do corpo da resposta para estorno por id:*
```bash
{
    "transacao": {
        "id": 1,
        "cartao": "************8983",
        "descricao": {
            "valor": 100.0,
            "dataHora": "01/05/2021 18:30:00",
            "estabelecimento": "Pet 100%",
            "nsu": "1",
            "codigoAutorizacao": "324264430",
            "status": "CANCELADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": 1
        }
    }
}
```

**``GET api/pagamentos/2``**: Envia uma requisição de consulta de pagamento por id informado na URL.
<br>
*Exemplo do corpo da resposta para consulta por id:*
```bash
{
    "transacao": {
        "id": 2,
        "cartao": "************8983",
        "descricao": {
            "valor": 100.0,
            "dataHora": "01/05/2021 18:30:00",
            "estabelecimento": "Pet 100%",
            "nsu": "2",
            "codigoAutorizacao": "285486170",
            "status": "AUTORIZADO"
        },
        "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": 1
        }
    }
}
```
**``GET api/pagamentos``**: Envia uma requisição de consulta de todos pagamentos.
<br>
*Exemplo do corpo da resposta para consulta de pagamentos
```bash
[
    {
        "transacao": {
            "id": 1,
            "cartao": "************8983",
            "descricao": {
                "valor": 100.0,
                "dataHora": "01/05/2021 18:30:00",
                "estabelecimento": "Pet 100%",
                "nsu": "1",
                "codigoAutorizacao": "324264430",
                "status": "CANCELADO"
            },
            "formaPagamento": {
                "tipo": "AVISTA",
                "parcelas": 1
            }
        }
    },
    {
        "transacao": {
            "id": 2,
            "cartao": "************8983",
            "descricao": {
                "valor": 100.0,
                "dataHora": "01/05/2021 18:30:00",
                "estabelecimento": "Pet 100%",
                "nsu": "2",
                "codigoAutorizacao": "285486170",
                "status": "AUTORIZADO"
            },
            "formaPagamento": {
                "tipo": "AVISTA",
                "parcelas": 1
            }
        }
    },
    {
        "transacao": {
            "id": 3,
            "cartao": "************8983",
            "descricao": {
                "valor": 100.0,
                "dataHora": "01/05/2021 18:30:00",
                "estabelecimento": "Pet 100%",
                "nsu": "3",
                "codigoAutorizacao": "377926503",
                "status": "AUTORIZADO"
            },
            "formaPagamento": {
                "tipo": "AVISTA",
                "parcelas": 1
            }
        }
    }
]
```
# Desenvolvedor

Adilson Junior - Desenvolvedor Java Pleno

LinkedIn: https://www.linkedin.com/in/adilson-junior-a646a488/
