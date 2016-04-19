package com.phoneBook.repository;

import com.phoneBook.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Component
//@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}
