package com.fbm.repository;


import com.fbm.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart on 07-Jul-17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
}
