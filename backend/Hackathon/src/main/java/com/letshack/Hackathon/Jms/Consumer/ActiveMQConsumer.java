package com.letshack.Hackathon.Jms.Consumer;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {

    public void receiveMessage() throws InterruptedException, JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("example-queue");

        MessageConsumer consumer = session.createConsumer(destination);

        // Receive messages (they will be delivered in priority order)
        for (int i = 0; i < 10001; i++) {
            TextMessage message = (TextMessage) consumer.receive();
            System.out.println("Received: " + message.getText() + " with priority: " + message.getJMSPriority());
        }

//        consumer.close();
//        session.close();
//        connection.close();
    }
}