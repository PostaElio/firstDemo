package alkemy.firstdemo.repository;

import alkemy.firstdemo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByEmail(String username);
    Boolean existsByEmail(String username);
}
