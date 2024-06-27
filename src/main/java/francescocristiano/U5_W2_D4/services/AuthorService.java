package francescocristiano.U5_W2_D4.services;

import francescocristiano.U5_W2_D4.entities.Author;
import francescocristiano.U5_W2_D4.entities.NewAuthorDTO;
import francescocristiano.U5_W2_D4.exeptions.NotFoundException;
import francescocristiano.U5_W2_D4.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    public Author saveAuthor(NewAuthorDTO authorBody) {

        this.authorRepository.findByEmail(authorBody.email()).ifPresent(author -> {
            throw new IllegalArgumentException("Email already in use");
        });
        Author newAuthor = new Author(authorBody.name(), authorBody.surname(), authorBody.email(), authorBody.birthDate());
        return this.authorRepository.save(newAuthor);
    }

    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    public Author findAuthorById(UUID id) {
        return this.authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author findAuthorByIdAndUpdate(UUID id, Author authorUpdated) {
        Author found = this.findAuthorById(id);
        found.setName(authorUpdated.getName());
        found.setSurname(authorUpdated.getSurname());
        found.setEmail(authorUpdated.getEmail());
        found.setBirthDate(authorUpdated.getBirthDate());
        found.setAvatar(authorUpdated.getAvatar());
        return this.authorRepository.save(found);
    }

    public void deleteAuthorById(UUID id) {
        this.authorRepository.deleteById(id);
    }


}



  /*  public List<Author> getAllAuthors() {
        return authors;
    }

    public Author findAuthorById(int id) {
        return authors.stream().filter(author -> author.getId() == id).findFirst().orElseThrow(() -> new NotFoundExpetion(id));
    }

    public Author findAuthorByIdAndUpdate(int id, Author authorUpdated) {
        Author found = this.findAuthorById(id);
        found.setName(authorUpdated.getName());
        found.setSurname(authorUpdated.getSurname());
        found.setEmail(authorUpdated.getEmail());
        found.setBirthDate(authorUpdated.getBirthDate());
        found.setAvatar(authorUpdated.getAvatar());
        return found;
    }

    public void deleteAuthorById(int id) {
        authors.removeIf(author -> author.getId() == id);
    }
}
*/
