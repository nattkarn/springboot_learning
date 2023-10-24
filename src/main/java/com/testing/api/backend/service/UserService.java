package com.testing.api.backend.service;


import com.testing.api.backend.entity.User;
import com.testing.api.backend.exception.BaseException;
import com.testing.api.backend.exception.UserException;
import com.testing.api.backend.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(String email, String password, String name, String token, Date tokenexpiredate) throws BaseException {
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
        if (repository.existsByEmail(email)) {
            throw UserException.createemailDuplicate();
        }

        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        entity.setToken(token);
        entity.setTokenExpire(tokenexpiredate);


        return repository.save(entity);
    }


    // not saved
//    @CachePut(value = "user", key = "#id")
    public User update(User user) {
        return repository.save(user);
    }

    //    @CachePut(value = "user", key = "#id")
    public User updateName(String id, String name) throws BaseException {
        Optional<User> opt = repository.findById(id);

        if (opt.isEmpty()) {
            throw UserException.notfound();
        }

        User user = opt.get();
        user.setName(name);
        return repository.save(user);
    }


    //    @CacheEvict(value = "user", key = "#id")
    public void deleteById(String id) {
        repository.deleteById(id);
    }


    //    @CacheEvict(value = "user", allEntries = true)
    public void deleteAll() {
//        repository.deleteAll();
    }


    public Optional<User> findByEmal(String email) {
        return repository.findByEmail(email);
    }

    //    @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public Optional<User> findById(String id) {
        log.info("Load user from DB: " + id);
        return repository.findById(id);
    }

    public Optional<User> findByToken(String token) {
        return repository.findByToken(token);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
