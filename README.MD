The microservice application is designed to periodically (every 2 minutes),
fetch currency exchange rates from an external API, such as the National Bank of Poland (NBP).

Upon retrieving the buying and selling rates for each currency, it forwards the information to 
a message queue (RabbitMQ) in separate messages for each currency.