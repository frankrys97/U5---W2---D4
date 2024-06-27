package francescocristiano.U5_W2_D4.controllers;


import francescocristiano.U5_W2_D4.entities.BlogPost;
import francescocristiano.U5_W2_D4.exeptions.BadRequestException;
import francescocristiano.U5_W2_D4.payloads.NewBlogPostDTO;
import francescocristiano.U5_W2_D4.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;


   /* @GetMapping
    private List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }*/

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return blogPostService.getAllBlogPosts(page, size, sortBy);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost saveBlogPost(@RequestBody @Validated NewBlogPostDTO blogPostPayload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new BadRequestException(validationResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        return blogPostService.saveBlogPost(blogPostPayload);
    }

    @GetMapping("/{id}")
    private BlogPost findBlogPostById(@PathVariable UUID id) {
        return blogPostService.findBlogPostById(id);
    }

    @PutMapping("/{id}")
    private BlogPost findBlogPostByIdAndUpdate(@PathVariable UUID id, @RequestBody BlogPost blogPostUpdated) {
        return blogPostService.findBlogPostByIdAndUpdate(id, blogPostUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteBlogPostById(@PathVariable UUID id) {
        blogPostService.deleteBlogPostById(id);
    }

    @PostMapping("/{id}/cover")
    public BlogPost uploadCover(@RequestParam("cover") MultipartFile file, @PathVariable UUID id) throws IOException {
        return blogPostService.uploadCover(file, id);
    }
}
