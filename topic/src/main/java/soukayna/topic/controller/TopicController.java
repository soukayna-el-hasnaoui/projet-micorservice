package soukayna.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soukayna.topic.model.Topic;
import soukayna.topic.service.TopicService;

import java.util.List;

@RestController
public class TopicController {

    //injecter mon service
    @Autowired
    private TopicService topicService;

    @GetMapping("/getAll")
    public List<Topic> getAll(){
        return topicService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Topic topic){
        try{
            var all = topicService.findAll();
            for (Topic r : all) {
                if (r.getName().equals(topic.getName())){
                    return ResponseEntity.status(400).body("topic already exist");
                }
            }
            topicService.save(topic);
            return  ResponseEntity.status(201).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }

    @GetMapping("/getById/{id}")
    public Topic getById(@PathVariable("id") Long id){
        var topic = topicService.findById(id);
        return topic.orElse(null);
    }

    @GetMapping("/getByRubriqueId/{rubriqueId}")
    public List<Topic> getByRubriqueId(@PathVariable("rubriqueId") Long rubriqueId){
        return topicService.findAllByRubriqueId(rubriqueId);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Topic topic){
        try{
            var topicTemp = topicService.findById(id);
            if (topicTemp.isEmpty())
                return ResponseEntity.status(404).build();
            topic.setId(id);
            topicService.save(topic);
            return  ResponseEntity.status(200).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try{
            topicService.deleteById(id);
            return ResponseEntity.status(200).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
