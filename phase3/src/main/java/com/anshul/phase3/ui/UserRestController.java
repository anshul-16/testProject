package com.anshul.phase3.ui;

import com.anshul.phase3.dto.UserDto;
import com.anshul.phase3.model.UserEntity;
import com.anshul.phase3.service.UserService;
import com.anshul.phase3.shared.UserRequestModelEntity;
import com.anshul.phase3.shared.UserResponseModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponseModelEntity> createUser(@RequestBody UserRequestModelEntity userDetails)
    {
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto=mapper.map(userDetails,UserDto.class);
        userService.createUser(userDto);
        UserResponseModelEntity userResponseModelEntity=mapper.map(userDto,UserResponseModelEntity.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModelEntity);
    }
    @GetMapping("/users/{userIdorEmail}")
    public  ResponseEntity<UserResponseModelEntity> findUserByUserIdorEmail(@PathVariable("userIdorEmail") String userIdorEmail) throws UserNotFoundException {
        String pattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if(!Pattern.matches(pattern,userIdorEmail)){
            UserEntity userEntity=userService.findByEmail(userIdorEmail);
            if(userEntity==null) {
                throw new UserNotFoundException("user not found with the email: "+userIdorEmail);
            }
            ModelMapper mapper=new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return ResponseEntity.status(HttpStatus.FOUND).body(mapper.map(userEntity,UserResponseModelEntity.class));
        }else{
            UserEntity userEntity=userService.findByUserId(userIdorEmail);
            if(userEntity==null) {
                throw new UserNotFoundException("user not found with the user id: "+userIdorEmail);
            }
            ModelMapper mapper=new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return ResponseEntity.status(HttpStatus.FOUND).body(mapper.map(userEntity,UserResponseModelEntity.class));
        }
    }
    @GetMapping("/users/userId={userId}/email={email}")
    public  ResponseEntity<UserResponseModelEntity> findUserByUserIdandEmail(@PathVariable("userId") String userId,@PathVariable("email") String email) throws UserNotFoundException {
        UserEntity userEntity1 = userService.findByEmail(email);
        UserEntity userEntity2 = userService.findByUserId(userId);

        if (userEntity1 == null || userEntity2 == null) {
            throw new UserNotFoundException("user not found with the email: " + email + "and uerId: " + userId);
        } else if (userEntity1 == userEntity2) {

            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return ResponseEntity.status(HttpStatus.FOUND).body(mapper.map(userEntity1, UserResponseModelEntity.class));
        }
        else{
            throw new UserNotFoundException("user not found with the email: " + email + "and uerId: " + userId);
        }
    }
}