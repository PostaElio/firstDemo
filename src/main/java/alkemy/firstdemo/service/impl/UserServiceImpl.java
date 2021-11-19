package alkemy.firstdemo.service.impl;

import alkemy.firstdemo.model.UserEntity;
import alkemy.firstdemo.repository.UserRepository;
import alkemy.firstdemo.service.UserService;
import alkemy.firstdemo.service.exception.EmptyInputException;
import alkemy.firstdemo.service.exception.ExistsByEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException("Email not found");
        return new User(userEntity.getEmail(),userEntity.getPassword(),new ArrayList<>());
    }

    @Override
    public UserEntity save(UserEntity user) throws DataIntegrityViolationException, ExistsByEmailException {
        String email = user.getEmail();
        String password = user.getPassword();
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ExistsByEmailException(user.getEmail());
        }
        if (email.length() == 0 || password.length() == 0 || email.equals(null) || password.equals(null) ) {
            throw new EmptyInputException("User");
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
