package soukayna.rubrique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import soukayna.rubrique.poco.Topic;
import soukayna.rubrique.proxy.TopicProxy;
import soukayna.rubrique.service.RubriqueService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TopicController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private RubriqueService rubriqueService;

    @Autowired
    TopicProxy topicProxy;

    @GetMapping("/topic/getAll/{rubriqueId}")
    public ResponseEntity<?> getAllTopic(@PathVariable Long rubriqueId){
        return topicProxy.getAllTopic(rubriqueId);
    }

    @GetMapping("/topic/get/{topicId}")
    public ResponseEntity<?> getTopic(@PathVariable Long topicId){
        var topic = restTemplate.getForObject("http://topic/getById/" + topicId, Topic.class);
        if (topic == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(topic);
    }

    @PostMapping("/topic/create/{rubriqueId}")
    public ResponseEntity<?> create(@RequestBody Topic topic, @PathVariable Long rubriqueId) {
        try {
            //faire verification ici
            var rubrique = rubriqueService.findById(rubriqueId);
            if(rubrique.isEmpty())
                return ResponseEntity.status(404).build();
            topic.setRubriqueId(rubriqueId);
            topic.setUserId(rubrique.get().getUserId());
            System.out.println(topic);
            restTemplate.postForObject("http://topic/create", topic, Topic.class);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
