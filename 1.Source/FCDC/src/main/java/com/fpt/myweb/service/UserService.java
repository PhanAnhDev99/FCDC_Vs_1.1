package com.fpt.myweb.service;

import com.fpt.myweb.dto.request.UserRequet;

import java.util.List;

public interface UserService {
    //get all user
    public List<UserRequet> getAllUser();
    //get by id
    public UserRequet getUser(long id);
    //create new User
    public UserRequet addUser(UserRequet userRequet);
    //Delete User
    public UserRequet deleteUser(long id);
    public UserRequet edit(long id , UserRequet userRequet);
    // search
    public List<UserRequet> searchByRole(long role_id);
    public List<UserRequet> searchByTesxt(String text);





}
