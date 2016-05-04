# Arquitetura Microserviços Odontoweb

Este projeto foi construido para armazenar as configurações da arquitetura para os microserviços do projeto odontoweb. Todos os microserviços precisam possuir esta biblioteca no classpath.

### Oque está send configurado neste projeto?

	* Authorization Filter
	* Generic Dependencies
	* Multi-tenancy

### Instalação

Para compilar o projeto rode o seguinte comando:

``
gradle clean build

```

Para publicar o projeto como uma biblioteca no repositório local do maven rode o seguinte comando:

``
gradle publishToMavenLocal

```
