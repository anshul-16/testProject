package com.anshul.phase3.service;

import com.anshul.phase3.dto.UserDto;
import com.anshul.phase3.model.UserEntity;
import com.anshul.phase3.shared.UserResponseModelEntity;

public interface UserService {

    public UserResponseModelEntity createUser(UserDto userDto);
    public UserEntity findByUserId(String userId);
    public UserEntity findByEmail(String email);
}
