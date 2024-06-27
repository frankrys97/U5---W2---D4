package francescocristiano.U5_W2_D4.controllers;

import francescocristiano.U5_W2_D4.entities.Author;
import francescocristiano.U5_W2_D4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    private List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping("/{id}")
    private Author findAuthorById(@PathVariable UUID id) {
        return authorService.findAuthorById(id);
    }

    @PutMapping("/{id}")
    private Author findAuthorByIdAndUpdate(@PathVariable UUID id, @RequestBody Author authorUpdated) {
        return authorService.findAuthorByIdAndUpdate(id, authorUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteAuthorById(@PathVariable UUID id) {
        authorService.deleteAuthorById(id);
    }
}
