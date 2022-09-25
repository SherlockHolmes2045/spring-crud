package com.sherlock2045.demo.repository;

import com.sherlock2045.demo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
