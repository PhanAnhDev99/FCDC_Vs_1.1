package com.fpt.myweb.convert;

import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {
    public User convertToUser(UserRequet userRequet){
        User user = new User();
        user.setUsername(userRequet.getUsername());
        user.setPassword(userRequet.getPassword());
        user.setFirstname(userRequet.getFirstname());
        user.setLastname((userRequet.getLastname()));
        user.setEmail(userRequet.getEmail());
        user.setPhone(userRequet.getPhone());
        user.setAddress(userRequet.getAddress());
        user.setBirthOfdate(userRequet.getBirthOfdate());

       return user;
    }
    public UserRequet convertToUserRequest(User user){
        UserRequet userRequet = new UserRequet();
        userRequet.setUsername(user.getUsername());
        userRequet.setPassword(user.getPassword());
        userRequet.setFirstname(user.getFirstname());
        userRequet.setLastname((user.getLastname()));
        userRequet.setEmail(user.getEmail());
        userRequet.setPhone(user.getPhone());
        userRequet.setAddress(user.getAddress());
        userRequet.setBirthOfdate(user.getBirthOfdate());
        userRequet.setRole_id(user.getRoles().getId());
        userRequet.setVillage_id(user.getVillage().getId());
        return userRequet;
    }

}
