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

    /**
     * Limpiamos la base de datos
     */
    @AfterEach
    void tearDown() {
        postService.deleteAll();
        userService.deleteAll();
    }

    /**
     * Obtenemos todos los post con un titulo pasada por parametro,
     * si nadie tiene el titulo devolvemos una lista vacia
     */
    @Test
    void getAllByTitle() {
        assertEquals(2,postService.getAllByTitle(title).size());
    }

    /**
     * Obtenemos todos los post con una categoria pasada por parametro,
     * si nadie tiene la categoria devolvemos una lista vacia
     */
    @Test
    void getAllByCategory() {
        assertEquals(2,postService.getAllByCategory("MEME").size());
    }

    /**
     * Obtenemos todos los post con un titulo y una categoria pasada por parametro,
     * si nadie tiene el titulo y la categoria devolvemos una lista vacia
     */
    @Test
    void getAllByTitleAndCategory() {
        assertEquals(2,postService.getAllByTitleAndCategory(title,"MEME").size());
    }

    /**
     * Obtenemos un post con un id existente
     */
    @Test
    void getById() {
        assertNotNull(postService.getById(post1.getId()));
    }

    /**
     * Intentamos obtener un post con un id no existente
     */
    @Test
    void tryGetByIdNotExists() {
        assertThrows(IdNotFoundException.class, () -> postService.getById(123*321+10L));
    }

    /**
     * Intentamos guardar un post con un id de un usuario que no pertenece a ningun usuario
     */
    @Test
    void trySaveWithIdFromUserNotExists(){
        UserEntity user = new UserEntity();
        user.setId(132*321+10L);
        post1.setAuthor(user);
        assertThrows(IdNotFoundException.class, () -> postService.save(post1));
    }

    /**
     * Eliminamos un post con un id existe
     */
    @Test
    void softDelete() {
        assertEquals(3,postService.getAll().size());
        postService.softDelete(post1.getId());
        assertEquals(2,postService.getAll().size());
    }

    /**
     * Intentamos eliminar un post con id que no "existe"
     */
    @Test
    void tryDeleteByIdNotExists() {
        assertEquals(3, postService.getAll().size());
        assertThrows(IdNotFoundException.class, () -> postService.softDelete(123*312+10L));
        assertEquals(3, postService.getAll().size());
    }
}