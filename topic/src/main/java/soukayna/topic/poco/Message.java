package soukayna.topic.poco;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private Long topicId;
    private Long userId;
    private String content;
    private String createdAt;
}
