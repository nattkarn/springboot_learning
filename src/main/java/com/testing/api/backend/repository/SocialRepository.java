package com.testing.api.backend.repository;

import com.testing.api.backend.entity.Social;
import com.testing.api.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);

}
