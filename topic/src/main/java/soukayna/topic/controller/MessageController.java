package soukayna.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import soukayna.topic.poco.Message;
import soukayna.topic.service.TopicService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    TopicService topicService;


    @GetMapping("/message/getAll/{topicId}")
    public ResponseEntity<?> getAllTopic(@PathVariable Long topicId){
        ResponseEntity<List<Message>> responseEntity =
                restTemplate.exchange("http://message/getByTopicId/" + topicId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
        List<Message> topics = responseEntity.getBody();
        if (topics == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(new ArrayList<>(topics));
    }

    @PostMapping("/message/create/{topicId}")
    public ResponseEntity<?> create(@RequestBody Message message, @PathVariable Long topicId) {
        try {
            //faire verification ici
            var topic = topicService.findById(topicId);
            if(topic.isEmpty())
                return ResponseEntity.status(404).body("{\"error\" : \"topic with id: " + topicId +" not found\"}");
            message.setTopicId(topicId);
            message.setUserId(topic.get().getUserId());
            System.out.println(message);
            restTemplate.postForObject("http://message/create", message, Message.class);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\" : \"some error occur\"}");
        }
    }

}
