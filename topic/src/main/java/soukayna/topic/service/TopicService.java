package soukayna.topic.service;

import org.springframework.data.jpa.repository.JpaRepository;
import soukayna.topic.model.Topic;

import java.util.List;

public interface TopicService extends JpaRepository<Topic, Long> {
    public List<Topic> findAllByRubriqueId(Long rubriqueId);
}
