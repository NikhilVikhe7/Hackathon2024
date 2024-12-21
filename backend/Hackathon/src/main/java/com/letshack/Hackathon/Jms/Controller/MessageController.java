package com.letshack.Hackathon.Jms.Controller;

import com.letshack.Hackathon.Jms.Producer.ActiveMQProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final ActiveMQProducer producer;

    public MessageController(ActiveMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage("example-queue", message);
        return "Message sent: " + message;
    }

    
}