package alkemy.firstdemo.service;

import alkemy.firstdemo.model.PostEntity;
import javassist.NotFoundException;

import java.util.List;

public interface PostService {

    List<PostEntity> getAllByTitle(String title);

    List<PostEntity> getAllByCategory(String category);

    List<PostEntity> getAllByTitleAndCategory(String title, String category);

    PostEntity getById(Long id) throws NotFoundException;

    PostEntity save(PostEntity post);

    PostEntity update(PostEntity post);

    void delete(Long id) throws NotFoundException;

    void deleteAll();

}
