package alkemy.firstdemo.controller;

import alkemy.firstdemo.controller.dto.ApiResponse;
import alkemy.firstdemo.controller.dto.PostCompactResponse;
import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    @GetMapping(params = {"title", "category"})
    public ResponseEntity<List<PostCompactResponse>> getAllByTitleAndCategory(
            @RequestParam("title") String title,
            @RequestParam("category") String category) {
        return new ResponseEntity<>(toPostCompactResponse(postService.getAllByTitleAndCategory(title,category)), HttpStatus.OK);
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
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PostEntity> save(@RequestBody PostEntity postEntity) {
        return new ResponseEntity<>(postService.save(postEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<PostEntity> update(@PathVariable("id") Long id, @RequestBody Map<Object,Object> map) {
        PostEntity postEntity = postService.getById(id);
        map.forEach((key,value) -> {
            Field field = ReflectionUtils.findField(PostEntity.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field,postEntity,value);
        });
        return new ResponseEntity<>(postService.save(postEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        postService.softDelete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Post with id " + id + " is deleted"), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteAll() {
        postService.deleteAll();
        return new ResponseEntity<>(new ApiResponse(true, "All post were deleted"), HttpStatus.OK);
    }
}
