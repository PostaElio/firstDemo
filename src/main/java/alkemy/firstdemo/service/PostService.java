package alkemy.firstdemo.service;

import alkemy.firstdemo.model.PostEntity;
import alkemy.firstdemo.service.exception.IdNotFoundException;

import java.util.List;

public interface PostService {

    List<PostEntity> getAllByTitle(String title);

    List<PostEntity> getAllByCategory(String category);

    List<PostEntity> getAllByTitleAndCategory(String title, String category);

    PostEntity getById(Long id) throws IdNotFoundException;

    PostEntity save(PostEntity post) throws IdNotFoundException;

    void softDelete(Long id) throws IdNotFoundException;

    void deleteAll();

    List<PostEntity> getAll();
}
