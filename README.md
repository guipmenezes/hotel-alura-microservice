# Aplicação Web do Hotel Alura desenvolvida na arquitetura de microsserviços

## Sobre o projeto:
Seguindo a sequência dos projetos do Hotel Alura (Desktop app e Rest API), este projeto é a implementação de um serviço de Hotelaria, que contém cadastro de Hospede e Reserva com sistema contra fraudes.

Essa versão de backend do ecossitema Alura apenas provê a REST API. **Não possui UI**. Logo logo estarei desenvolvendo o Front-end em Angular.

A arquitetura utilizada foi a de microserviços, em que cada um dos serviços tem o seu próprio banco de dados e interagem entre si por meio de uma comunicação híbrida (assíncrona entre serviços de reservas e hospedes, síncrona entre serviço de hospede e fraude).
Quando o cliente tem contato com o serviço e insere seus dados, esses dados são tratados pelo Load Balancer (que por padrão é configurado na arquitetura Round-Robin) e ao concluir o seu cadastro, o microserviço de fraude também verifica se aquele hospede é válido.
<br>

### Como rodar:
Para rodar essa webapplication, é necessário seguir os passos:
* Executar o Jar do Zipkin para fazer o rastreamento distribuído;
* Criar as bases de dados no PostgreSQL para os microsserviços de reservas, hospedes e fraude;
* Ajustar as configurações no config server e nas configurações das aplicações (application.yml);
* É possível testar a aplicação por meio do Postman, utilizando das rotas pré-configuradas:
  * POST - Hospede: http://localhost:8089/hospedes (necessário seguir estrutura para postagem)
  * GET - Hospede: http://localhost:8089/hospedes
  * DELETE - Hospede: http://localhost:8089/hospedes/{id do hospede}
  * POST - Reserva: http://localhost:8089/reservas (necessário seguir estrutura para postagem)
  * GET - Reserva: http://localhost:8089/reservas
  * DELETE - Reserva: http://localhost:8089/reservas/1
  * GET - FraudeCheck: http://localhost:8089/fraude-check/{id do hospede}
  * GET - Hospede com a Reserva: http://localhost:8089/hospedes/reserva/{id do hospede}

### Exemplificação de funcionamento:
<img src="https://user-images.githubusercontent.com/86692306/213831794-44d95f2d-e760-4b2e-a676-1201e72a79df.png" />
<br>

## 🖥️ Tecnologias utilizadas:
* Java 18
* Spring Boot 3.0
* Maven
* Spring Data JPA
* Spring Cloud 2022.0.0
* Spring Cloud Netflix Eureka Server/Client
* Spring Cloud Config Server
* Docker
* RabbitMQ
* Zipkin
* PostreSQL
* Testes unitários
  * JUnit e Mockito

### Config Server:
Com a implementação do Cloud Config Server, optei por armazenar as configurações no próprio github.
Link: https://github.com/guipmenezes/hotel-alura-config-server

### Próximas implementações

Uma das últimas features que estou buscando implementar é um serviço de Notificação com Twilio ou Firebase.
<br>
<br>
