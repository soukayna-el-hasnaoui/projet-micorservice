package soukayna.topic.service;

import org.springframework.data.jpa.repository.JpaRepository;
import soukayna.topic.model.Message;

import java.util.List;

public interface MessageService extends JpaRepository<Message, Long> {
    public List<Message> findAllByTopicId(Long topicId);
}
