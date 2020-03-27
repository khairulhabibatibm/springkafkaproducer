package application;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController{

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/test")
    public String sendTestMessage(){
        Random r = new Random();
        String number = String.valueOf(r.nextInt());
        try {
            kafkaTemplate.send("test-topic","this is number " + number);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Success " + number;
    }

}