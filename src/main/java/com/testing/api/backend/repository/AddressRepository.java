package com.testing.api.backend.repository;

import com.testing.api.backend.entity.Address;
import com.testing.api.backend.entity.Social;
import com.testing.api.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);

}
