package francescocristiano.U5_W2_D4.exeptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {

    public NotFoundException(UUID blogPostId) {
        super("BlogPost with id " + blogPostId + " not found");
    }
}
