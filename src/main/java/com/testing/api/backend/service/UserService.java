package com.testing.api.backend.service;


import com.testing.api.backend.entity.User;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.UserException;
import com.testing.api.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final  UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(String email, String password, String name) throws BaseException {
        //validate
        if (Objects.isNull(email)) {
            throw UserException.createemailNull();
        }

        if (Objects.isNull(password)) {
            throw UserException.createpasswordtNull();
        }

        if (Objects.isNull(name)) {
            throw UserException.createnameNull();
        }


        //verify
        if (repository.existsByEmail(email)){
            throw UserException.createemailDuplicate();
        }

        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        return repository.save(entity);
    }
// not saved
//    public User Update(User user){
//        return repository.save(user);
//    }

    public User updateName(String id,String name) throws BaseException {
        Optional<User> opt = repository.findById(id);

        if (opt.isEmpty()){
            throw UserException.notfound();
        }

        User user = opt.get();
        user.setName(name);
        return repository.save(user);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }




    public Optional<User> findByEmal(String email){
        return repository.findByEmail(email);
    }

    public Optional<User> findById(String Id){
        return repository.findById(Id);
    }

    public boolean matchPassword(String rawPassword,String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
