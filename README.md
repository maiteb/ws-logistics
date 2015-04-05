# ws-logistics

Este é um projeto para calcular a distância com menor custo entre dois pontos, onde custo equivale a distância entre os pontos dividido pela autonomia do veículo.

É um projeto WEB Java 8, para executá-lo deve-se primeiro construir o projeto com Maven:

**mvn clean install eclipse:eclipse**

Utilizando o Tomcat 8, deve-se deployar o arquivo ws-logistics.war

Existem dois serviços REST:

**POST http://localhost:[TOMCAT_PORT]/wslogistics/logisticsNetwork/new**

O primeiro recebe um JSON do seguinte formato:

```
{
  "name": "RJ",
  "links": [
    {
      "sourceNode": "A",
      "destinationNode": "B",
      "distance": "4"
    },
    {
      "sourceNode": "A",
      "destinationNode": "C",
      "distance": "3"
    },
    {
      "sourceNode": "B",
      "destinationNode": "C",
      "distance": "43"
    },
    {
      "sourceNode": "C",
      "destinationNode": "D",
      "distance": "12"
    },
    {
      "sourceNode": "B",
      "destinationNode": "D",
      "distance": "5"
    }
  ]
}
```

Ele armazenará a rede em um arquivo salvo em (user.dir)/networks e em um cache. Toda vez que a aplicação é inicializada, o cache é carregado com todos os arquivos especificados.

**GET http://localhost:[TOMCAT_PORT]/wslogistics/route?net=SP&source=A&destination=D&autonomy=10&costPerLiter=2.5**

Este segundo é um exemplo da chamada para o serviço para calcular efetivamente uma rota, passando como parâmetros:

- net: nome da rede
- source: nó inicial
- destination: nó final
- autonomy: autonomia do veículo
- costPerLiter: custo do litro de combustível

A saída será um JSON no seguinte formato:

```
{
path: "A, B, D"
cost: 6.25
}
```

Quando não existir caminho, a saída será a seguinte:

```
{
path: ""
cost: -1
}
```

------------------------

A escolha pelo padrão REST é pela maior facilidade em compreender o que está transitando na rede (sem envelopes SOAP complexos) e utilização de padrões que não estão amarrados a nenhuma linguagem de programação específica.

