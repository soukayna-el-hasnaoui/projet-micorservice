package soukayna.topic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soukayna.topic.model.Message;
import soukayna.topic.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MessageController {

    //injecter mon service
    @Autowired
    private MessageService messageService;

    @GetMapping("/getByTopicId/{topicId}")
    public List<Message> getAll(@PathVariable Long topicId){
        return messageService.findAllByTopicId(topicId);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Message message){
        try{
            messageService.save(message);
            return  ResponseEntity.status(201).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try{
            messageService.deleteById(id);
            return ResponseEntity.status(200).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();

        }
    }


}
