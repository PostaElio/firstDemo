package alkemy.firstdemo.controller;

import alkemy.firstdemo.model.PostEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {



    @GetMapping()
    public PostEntity getById(){
        return new PostEntity();

    }


}
