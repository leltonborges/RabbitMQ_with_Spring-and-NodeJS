const amqp = require('amqplib');
const queue = 'queue.price'

amqp.connect({
    protocol: 'amqp',
    hostname: 'localhost',
    port: 5672,
    username: 'guest',
    password: 'guest'
}).then(conn => {
    conn.createChannel()
        .then(channel => {
            channel.consume(queue, (message) => {
                console.log(message.content.toString())
            }, {
                noAck: true
            })
        })
        .catch(console.warn);
})
.catch(console.warn);