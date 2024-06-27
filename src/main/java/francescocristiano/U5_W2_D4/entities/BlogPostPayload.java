package francescocristiano.U5_W2_D4.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BlogPostPayload {
    private String category;
    private String title;
    private String body;
    private int readingTime;
    private UUID authorId;

    public BlogPostPayload(String category, String title, String body, int readingTime, UUID authorId) {
        this.category = category.toUpperCase();
        this.title = title;
        this.body = body;
        this.readingTime = readingTime;
        this.authorId = authorId;
    }
}
