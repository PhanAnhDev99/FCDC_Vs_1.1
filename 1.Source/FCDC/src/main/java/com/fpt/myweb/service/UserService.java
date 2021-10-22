package com.fpt.myweb.service;

import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.User;

import java.util.List;

public interface UserService {
    //get all user
    public List<UserRequet> getAllUser();
    //get by id
    public UserRequet getUser(long id);
    //create new User
    public User addUser(UserRequet userRequet);
    //Delete User
    public UserRequet deleteUser(long id);
    public UserRequet edit(UserRequet userRequet);
    // search
    public List<UserRequet> searchByRole(long role_id, Integer page);

    public int countByRole(long role_id);

    public List<UserRequet> searchByTesxt(String text, Integer page);

    public int countByTesxt(String text);

    public List<UserRequet> getAllUserByPage(Integer page);

    public User login(String username, String password);

}
