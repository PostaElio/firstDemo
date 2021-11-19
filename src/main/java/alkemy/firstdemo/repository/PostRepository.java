package alkemy.firstdemo.repository;

import alkemy.firstdemo.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {

    List<PostEntity> getAllByTitle(String title);
    List<PostEntity> getAllByCategory(String category);
    List<PostEntity> getAllByTitleAndCategory(String title, String category);

}
