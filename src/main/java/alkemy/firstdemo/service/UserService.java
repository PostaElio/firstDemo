package alkemy.firstdemo.service;

import alkemy.firstdemo.model.UserEntity;
import alkemy.firstdemo.service.exception.ExistsByEmailException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserEntity save(UserEntity user) throws DataIntegrityViolationException, ExistsByEmailException;
    void deleteAll();
}
