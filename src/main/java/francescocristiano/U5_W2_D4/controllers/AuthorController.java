package francescocristiano.U5_W2_D4.controllers;

import francescocristiano.U5_W2_D4.entities.Author;
import francescocristiano.U5_W2_D4.exeptions.BadRequestException;
import francescocristiano.U5_W2_D4.payloads.NewAuthorDTO;
import francescocristiano.U5_W2_D4.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private Author saveAuthor(@RequestBody @Validated NewAuthorDTO authorBody, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return authorService.saveAuthor(authorBody);
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

    @PostMapping("/{id}/avatar")
    public String uploadCover(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id) throws IOException {
        return authorService.uploadCover(file, id);
    }
}
