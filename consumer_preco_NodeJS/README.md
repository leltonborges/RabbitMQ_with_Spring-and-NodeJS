# Consumindo messagem com NodeJS no RabbitMQ

## Requisitos
 Ter instalado os seguintes software:
 + Nodejs 10+
 + Yarn ou Npm

## Executando
No terminal execute:
+ Baixando os modulos
```shell
  $ yarm install
  # ou
  $ npm install
```
+ Executando
```shell
 $ node src/consumer.js 
```

+ Saida
```shell
{"codProducto":72,"price":152}
{"codProducto":72,"price":152}
{"codProducto":72,"price":152}
{"codProducto":72,"price":152}
{"codProducto":72,"price":152}
{"codProducto":72,"price":152}
{"codProducto":72,"price":152}
{"codProducto":192,"price":84616.5}
{"codProducto":516,"price":816.5}

```