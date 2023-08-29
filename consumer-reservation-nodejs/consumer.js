const amqp = require("amqplib");

const queue = "RESERVATION";

amqp
  .connect({
    host: "localhost",
    port: 5672,
    username: "guest",
    password: "guest",
  })
  .then((connect) =>
    connect
      .createChannel()
      .then((channel) => {
        channel.consume(
          queue,
          (message) => {
            console.log(message.content.toString());
          },
          { noAck: true }
        );
      })
      .catch((erro) => console.log(erro))
  )
  .catch((erro) => console.log(erro));
