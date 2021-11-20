package alkemy.firstdemo.service;

import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.model.UserEntity;
import alkemy.firstdemo.service.exception.IdNotFoundException;
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

        title = "First Post";

        user1 = userService.save(new UserEntity("email1@gmail.com","password"));

        post1 = postService.save(new PostEntity(title,
                "This is the best post in the world",
                "https://www.mejoresimagenes.com/images/1321654.png",
                "MEME",
                new Date(),
                user1));
        post2 = postService.save(new PostEntity("Second Post",
                "This is my second post",
                "https://www.mejoresimagenes.com/images/13211232654.png",
                "WORK",
                new Date(),
                user1));

        user2 = userService.save(new UserEntity("email2@gmail.com","password"));

        post2 = postService.save(new PostEntity(title,
                "This is the best post in the world",
                "https://www.mejoresimagenes.com/images/1321123654.png",
                "MEME",
                new Date(),
                user2));
    }

    @AfterEach
    void tearDown() {
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    void getAllByTitle() {
        assertEquals(2,postService.getAllByTitle(title).size());
    }

    @Test
    void getAllByCategory() {
        assertEquals(2,postService.getAllByCategory("MEME").size());
    }

    @Test
    void getAllByTitleAndCategory() {
        assertEquals(2,postService.getAllByTitleAndCategory(title,"MEME").size());
    }

    @Test
    void getById() {
        assertNotNull(postService.getById(post1.getId()));
    }

    @Test
    void tryGetByIdNotExists() {
        assertThrows(IdNotFoundException.class, () -> postService.getById(123*321+10L));
    }

    @Test
    void trySaveWithIdFromUserNotExists(){
        UserEntity user = new UserEntity();
        user.setId(132*321+10L);
        post1.setAuthor(user);
        assertThrows(IdNotFoundException.class, () -> postService.save(post1));
    }

    @Test
    void update() {
        post1.setTitle("Este titulo me gusta mucho mas");
        postService.update(post1);
        assertEquals("Este titulo me gusta mucho mas", postService.getById(post1.getId()).getTitle());
    }
    @Test
    void tryUpdateIdNotExists() {
        post1.setTitle("Este titulo me gusta mucho mas");
        post1.setId(123*321+10L);
        assertThrows(IdNotFoundException.class , () -> postService.update(post1));
    }

    @Test
    void delete() {
        assertEquals(3,postService.getAll().size());
        postService.delete(post1.getId());
        assertEquals(2,postService.getAll().size());
    }

    @Test
    void tryDeleteByIdNotExists() {
        assertEquals(3, postService.getAll().size());
        assertThrows(IdNotFoundException.class, () -> postService.delete(123*312+10L));
        assertEquals(3, postService.getAll().size());
    }
}