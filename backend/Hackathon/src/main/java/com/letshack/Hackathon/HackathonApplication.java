package com.letshack.Hackathon;

import com.letshack.Hackathon.Jms.Consumer.ActiveMQConsumer;
import com.letshack.Hackathon.model.User;
import com.letshack.Hackathon.repository.UserRepository;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJms
public class HackathonApplication implements CommandLineRunner {
	private final ActiveMQConsumer activeMQConsumer;

    public HackathonApplication(ActiveMQConsumer activeMQConsumer) {
        this.activeMQConsumer = activeMQConsumer;
    }

    public static void main(String[] args) throws JMSException {
		SpringApplication.run(HackathonApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("example-queue");

		MessageProducer producer = session.createProducer(destination);
		var orderIds=generateOrderIds();
		Map<String,String> map = new HashMap<>();
		for(String str:orderIds){
			TextMessage message = session.createTextMessage(str);

			// Send the message


			// Get the messageId of the sent message
			if(str.equals("10")){
				producer.send(message, DeliveryMode.NON_PERSISTENT, 9,10000); // Setting priority 9
			}else{
				producer.send(message);
			}
			String messageId = message.getJMSMessageID();
			map.put(str,messageId);
			System.out.println("Sent message with ID: " + messageId);
		}

		activeMQConsumer.receiveMessage();

//		producer.close();
//		session.close();
//		connection.close();
	}

	private List<String> generateOrderIds() {
		List<String> arr = new ArrayList<>();
		for(int i=0;i<1000;i++){
			arr.add(String.valueOf(i));
		}
		return arr;
	}
}

