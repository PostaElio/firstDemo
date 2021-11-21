package alkemy.firstdemo.repository;

import alkemy.firstdemo.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {

    List<PostEntity> getAllByTitle(String title);
    List<PostEntity> getAllByCategory(String category);
    List<PostEntity> getAllByTitleAndCategory(String title, String category);

    @Transactional
    @Modifying
    @Query(value = "update posts p set p.softdelete = true where p.id = :id")
    void softDelete(Long id);

    @Transactional
    @Modifying
    @Query(value = "update posts p set p.softdelete = true")
    void softDeleteAll();

    @Transactional
    @Modifying
    @Query(value = "delete from posts p WHERE p.softdelete = true")
    void deleteAll();

}
