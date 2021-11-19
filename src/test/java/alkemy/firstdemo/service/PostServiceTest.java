package alkemy.firstdemo.service;

import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.model.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    private UserEntity user1, user2;
    private PostEntity post1, post2, post3;


    private String title;

    @BeforeEach
    void setUp() {

        title = "This is the best post in the world";


        user1 = userService.save(new UserEntity("email@gmail.com","password"));


        post1 = postService.save(new PostEntity("First Post",
                "This is the best post in the world",
                "https://www.mejoresimagenes.com/images/1321654.png",
                "MEME",
                new Date(),
                user1));

    }

    @AfterEach
    void tearDown() {
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    void getAllByTitle() {
        //assertEquals(0,user1.getId());
        //assertEquals(0,post1.getId());
        assertEquals(1,postService.getAllByTitle("This is the best post in the world").size());
    }

    @Test
    void getAllByCategory() {
    }

    @Test
    void getAllByTitleAndCategory() {
    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}