package francescocristiano.U5_W2_D4.repositories;

import francescocristiano.U5_W2_D4.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findByEmail(String email);
}
