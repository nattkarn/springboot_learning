package com.testing.api.backend.service;


import com.testing.api.backend.entity.Address;
import com.testing.api.backend.entity.User;
import com.testing.api.backend.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;

    }

    public List<Address> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipcode) {

        //TODO: validate


        //save
        Address entity = new Address();
        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);
        return repository.save(entity);
    }

}
