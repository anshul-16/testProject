package com.anshul.phase3.dao;

import com.anshul.phase3.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    public UserEntity findByUserId(String userId);
    public UserEntity findByEmail(String email);
}

