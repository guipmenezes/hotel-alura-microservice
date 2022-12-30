# Aplicação Web do Hotel Alura desenvolvida na arquitetura de Microservice

## 🖥️ Tecnologias utilizadas:
* Java 18
* Spring Boot 3.0
* Maven
* Spring Data JPA
* Spring Cloud 2022.0.0
* Spring Cloud Netflix Eureka Server/Client
* Docker
* PostreSQL
* Apache Kafka (Implementando serviço de mensageria)

## Sobre o projeto:
Seguindo a sequência dos projetos do Hotel Alura (Desktop app e Rest API), este projeto é uma implementação do serviço de Hospede com sistema contra fraudes.
A arquitetura utilizada foi a de microserviços, em que cada um dos serviços tem o seu próprio banco de dados e interagem entre si por meio de uma comunicação síncrona.
Quando o cliente tem contato com o serviço e insere seus dados, esses dados são tratados pelo Load Balancer (que por padrão é configurado na arquitetura Round-Robin) e ao concluir o seu cadastro, o microserviço de fraude também verifica se aquele hospede é válido.
<br>
### Exemplificação de funcionamento:
<img src="https://user-images.githubusercontent.com/86692306/210026729-79fd737d-7eac-4e65-a023-debe86068da0.png" />

<br>

### Próximas implementações

Como qualquer boa arquitetura de microserviço, já estou implementando um serviço de Message Queue (comumente conhecido como Mensageria) com o Apache Kafkae e talvez um serviço de Notificação com Twilio ou Firebase. Também estou pensando na melhor ferramenta para criar o Config Server, até o momento estou optando pelo Spring Cloud Config. Também pretendo implementar um sistema de tracing por meio do Zipkin.
<br>
<br>
Em um futuro mais distante, estou querendo implementar o Kubernetes.
