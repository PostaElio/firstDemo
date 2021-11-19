package alkemy.firstdemo.controller;

import alkemy.firstdemo.controller.dto.ApiResponse;
import alkemy.firstdemo.controller.dto.PostCompactResponse;
import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(params = "title")
    public ResponseEntity<List<PostCompactResponse>> getAllByTitle(@RequestParam("title") String title) {
        return new ResponseEntity<>(toPostCompactResponse(postService.getAllByTitle(title)), HttpStatus.OK);
    }

    @GetMapping(params = "category")
    public ResponseEntity<List<PostCompactResponse>> getAllByCategory(@RequestParam("category") String category) {
        return new ResponseEntity<>(toPostCompactResponse(postService.getAllByCategory(category)), HttpStatus.OK);
    }

    /*/movies/220/character/184
    @PutMapping("/{movieId}/character/{characterId}")
    public ResponseEntity<ApiResponse> addCharacterToMovie(@PathVariable Long movieId, @PathVariable Long characterId) {
        try {/posts?titulo=TITULO&category=CATEGORY
     */
    @GetMapping("/{titulo}")
    public ResponseEntity<List<PostCompactResponse>> getAllByTitleAndCategory(@RequestParam("category") String category) {
        return new ResponseEntity<>(toPostCompactResponse(postService.getAllByCategory(category)), HttpStatus.OK);
    }
    private List<PostCompactResponse> toPostCompactResponse(List<PostEntity> postEntities) {
        ModelMapper modelMapper = new ModelMapper();
        return postEntities
                .stream().map(postEntity ->
                        modelMapper.map(postEntity, PostCompactResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostEntity> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<PostEntity> save(@RequestBody PostEntity postEntity) {
        return new ResponseEntity<>(postService.save(postEntity), HttpStatus.OK);
    }

    //PostEntity update(PostEntity post);

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "Movie with id " + id + " is deleted"), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteAll() {
        postService.deleteAll();
        return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "All Movies are deleted"), HttpStatus.OK);
    }
}
