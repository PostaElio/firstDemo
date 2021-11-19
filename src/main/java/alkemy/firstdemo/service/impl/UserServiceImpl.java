package alkemy.firstdemo.service.impl;

import alkemy.firstdemo.model.UserEntity;
import alkemy.firstdemo.repository.UserRepository;
import alkemy.firstdemo.service.UserService;
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
    public UserEntity save(UserEntity user) throws DataIntegrityViolationException {
        try {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("The email is already taken.");
        }
    }
    @Override
    public boolean existsWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
