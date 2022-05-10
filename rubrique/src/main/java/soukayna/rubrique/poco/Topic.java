package soukayna.rubrique.poco;

import lombok.Data;

@Data
public class Topic {
    private Long id;
    private String name;
    private Long rubriqueId;
    private Long userId;
    private String createdAt;
}
