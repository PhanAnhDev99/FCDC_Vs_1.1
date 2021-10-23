package com.fpt.myweb.convert;

import com.fpt.myweb.common.Contants;
import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.User;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserConvert {
    public static User convertToUser(UserRequet userRequet) throws ParseException {
        User user = new User();
        user.setUsername(userRequet.getUsername());
        user.setPassword(userRequet.getPassword());
        user.setFirstname(userRequet.getFirstname());
        user.setLastname((userRequet.getLastname()));
        user.setEmail(userRequet.getEmail());
        user.setPhone(userRequet.getPhone());
        user.setAddress(userRequet.getAddress());
        Date date = new SimpleDateFormat(Contants.DATE_FORMAT).parse(userRequet.getBirthOfdate());
        user.setBirthOfdate(date);

       return user;
    }
    public static UserRequet convertToUserRequest(User user){
        UserRequet userRequet = new UserRequet();
        userRequet.setId(user.getId());
        userRequet.setUsername(user.getUsername());
        userRequet.setPassword(user.getPassword());
        userRequet.setFirstname(user.getFirstname());
        userRequet.setLastname((user.getLastname()));
        userRequet.setEmail(user.getEmail());
        userRequet.setPhone(user.getPhone());
        userRequet.setAddress(user.getAddress());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        userRequet.setBirthOfdate(dateFormat.format(user.getBirthOfdate()));
        userRequet.setRole_id(user.getRole().getId());
        userRequet.setVillage_id(user.getVillage().getId());
        return userRequet;
    }

}
