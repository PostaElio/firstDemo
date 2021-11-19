package alkemy.firstdemo.service;

import alkemy.firstdemo.model.UserEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity save(UserEntity user) throws DataIntegrityViolationException;
    boolean existsWithEmail(String email);
    void deleteAll();
}
