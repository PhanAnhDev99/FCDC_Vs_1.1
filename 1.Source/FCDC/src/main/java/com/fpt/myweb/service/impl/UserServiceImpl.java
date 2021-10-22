package com.fpt.myweb.service.impl;




import com.fpt.myweb.common.Contants;
import com.fpt.myweb.convert.UserConvert;
import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.Role;
import com.fpt.myweb.entity.User;
import com.fpt.myweb.entity.Village;
import com.fpt.myweb.exception.AppException;
import com.fpt.myweb.exception.ErrorCode;
import com.fpt.myweb.repository.RoleRepository;
import com.fpt.myweb.repository.UserRepository;
import com.fpt.myweb.repository.VillageRepository;
import com.fpt.myweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private VillageRepository villageRepository;
    @Autowired
    private UserConvert userConvert;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserRequet> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserRequet> userRequets = new ArrayList<>();
        for (User user:userList){
            userRequets.add(userConvert.convertToUserRequest(user));
        }
        return userRequets;
    }

    @Override
    public UserRequet getUser(long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ID.getKey(), ErrorCode.NOT_FOUND_ID.getValue() + id));
        UserRequet userRequet = userConvert.convertToUserRequest(user);
        return userRequet;
    }

    @Override
    public User addUser(UserRequet userRequet) {
        Role role = roleRepository.findById(userRequet.getRole_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ROLE_ID.getKey(), ErrorCode.NOT_FOUND_ROLE_ID.getValue() + userRequet.getRole_id()));
        Village village = villageRepository.findById(userRequet.getVillage_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_VILLAGE_ID.getKey(), ErrorCode.NOT_FOUND_VILLAGE_ID.getValue() + userRequet.getVillage_id()));
        User user = userConvert.convertToUser(userRequet);
        user.setRoles(role);
        user.setVillage(village);
        user.setUsername(userRequet.getUsername());
        user.setPassword(passwordEncoder.encode(userRequet.getPassword()));
        user.setAddress(userRequet.getAddress());
        user.setEmail(userRequet.getEmail());
        user.setBirthOfdate(userRequet.getBirthOfdate());
        user.setFirstname(userRequet.getFirstname());
        user.setLastname(userRequet.getLastname());
        user.setPhone(userRequet.getPhone());
        user.setImageUrl(userRequet.getImageUrl());
        user.setCreatedDate(new Date());
        User user1 = userRepository.save(user);
        return user1;
    }

    @Override
    public UserRequet deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ID.getKey(), ErrorCode.NOT_FOUND_ID.getValue() + id));
        UserRequet userRequet = userConvert.convertToUserRequest(user);
        userRepository.delete(user);
        return userRequet;
    }

    @Override
    public UserRequet edit(UserRequet userRequet) {
        User user = userRepository.findById(userRequet.getId()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ID.getKey(), ErrorCode.NOT_FOUND_ID.getValue() + userRequet.getId()));
        Role role = roleRepository.findById(userRequet.getRole_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ROLE_ID.getKey(), ErrorCode.NOT_FOUND_ROLE_ID.getValue() + userRequet.getRole_id()));
        Village village = villageRepository.findById(userRequet.getVillage_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_VILLAGE_ID.getKey(), ErrorCode.NOT_FOUND_VILLAGE_ID.getValue() + userRequet.getVillage_id()));
        user.setRoles(role);
        user.setVillage(village);
        user.setUsername(userRequet.getUsername());
        user.setPassword(passwordEncoder.encode(userRequet.getPassword()));
        user.setAddress(userRequet.getAddress());
        user.setEmail(userRequet.getEmail());
        user.setBirthOfdate(userRequet.getBirthOfdate());
        user.setFirstname(userRequet.getFirstname());
        user.setLastname(userRequet.getLastname());
        user.setPhone(userRequet.getPhone());
        user.setImageUrl(userRequet.getImageUrl());
        user.setModifiedDate(new Date());
        UserRequet userRequet1 = userConvert.convertToUserRequest(userRepository.save(user));
        return userRequet1;
    }

    @Override
    public List<UserRequet> searchByRole( long role_id, Integer page) {
        if(page == null){
            page = 1;
        }
        Integer offset = Contants.PAGE_SIZE * (page - 1);
        Role role = roleRepository.findById(role_id).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ROLE_ID.getKey(), ErrorCode.NOT_FOUND_ROLE_ID.getValue() + role_id));
        List<User> searchList = userRepository.findByRoles(role, Contants.PAGE_SIZE, offset);
        List<UserRequet> userRequets = new ArrayList<>();
        for (User user:searchList){
            userRequets.add(userConvert.convertToUserRequest(user));
        }
        return userRequets;
    }

    @Override
    public int countByRole(long role_id) {
        Role role = roleRepository.findById(role_id).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ROLE_ID.getKey(), ErrorCode.NOT_FOUND_ROLE_ID.getValue() + role_id));
        List<User> searchList = userRepository.findByRoles(role);
        if(searchList == null){
            return 0;
        }
        return searchList.size();
    }

    @Override
    public List<UserRequet> searchByTesxt(String text, Integer page) {
        if(page == null){
            page = 1;
        }
        Integer offset = Contants.PAGE_SIZE * (page - 1);
        List<User> searchList = userRepository.findByUsernameContaining(text);
        List<UserRequet> userRequets = new ArrayList<>();
        for (User user:searchList){
            userRequets.add(userConvert.convertToUserRequest(user));
        }
        return userRequets;
    }

    @Override
    public int countByTesxt(String text) {
        List<User> searchList = userRepository.findByUsernameContaining(text);
        if(searchList == null){
            return 0;
        }
        return searchList.size();
    }

    @Override
    public List<UserRequet> getAllUserByPage(Integer page) {
        if(page == null){
            page = 1;
        }
        Integer offset = Contants.PAGE_SIZE * (page - 1);
        return userRepository.getAllUserByPage(Contants.PAGE_SIZE, offset);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password));
    }


}
