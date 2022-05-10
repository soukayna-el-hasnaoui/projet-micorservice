package soukayna.rubrique.proxy;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import soukayna.rubrique.poco.Topic;
import soukayna.rubrique.service.RubriqueService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicProxy {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getAllTopicFallBack")
    public ResponseEntity<?> getAllTopic(@PathVariable Long rubriqueId){
        ResponseEntity<List<Topic>> responseEntity =
                restTemplate.exchange("http://topic/getByRubriqueId/" + rubriqueId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Topic>>() {});
        List<Topic> topics = responseEntity.getBody();
        if (topics == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(new ArrayList<>(topics));
    }

    public ResponseEntity<?> getAllTopicFallBack(Long rubriqueId){
        System.out.println("in fallbackMethod");
        return ResponseEntity.ok(new ArrayList<Topic>());
    }
}
