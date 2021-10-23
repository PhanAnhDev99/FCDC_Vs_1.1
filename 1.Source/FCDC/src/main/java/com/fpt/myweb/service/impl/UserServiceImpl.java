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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
    public User addUser(UserRequet userRequet) throws ParseException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role = roleRepository.findById(userRequet.getRole_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ROLE_ID.getKey(), ErrorCode.NOT_FOUND_ROLE_ID.getValue() + userRequet.getRole_id()));
        Village village = villageRepository.findById(userRequet.getVillage_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_VILLAGE_ID.getKey(), ErrorCode.NOT_FOUND_VILLAGE_ID.getValue() + userRequet.getVillage_id()));
        User user = userConvert.convertToUser(userRequet);
        user.setRole(role);
        user.setVillage(village);
        user.setUsername(userRequet.getUsername());
        user.setPassword(passwordEncoder.encode(userRequet.getPassword()));
        user.setAddress(userRequet.getAddress());
        user.setEmail(userRequet.getEmail());
        Date date = new SimpleDateFormat(Contants.DATE_FORMAT).parse(userRequet.getBirthOfdate());
        user.setBirthOfdate(date);
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
    public UserRequet edit(UserRequet userRequet) throws ParseException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(userRequet.getId()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ID.getKey(), ErrorCode.NOT_FOUND_ID.getValue() + userRequet.getId()));
        Role role = roleRepository.findById(userRequet.getRole_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_ROLE_ID.getKey(), ErrorCode.NOT_FOUND_ROLE_ID.getValue() + userRequet.getRole_id()));
        Village village = villageRepository.findById(userRequet.getVillage_id()).orElseThrow(()
                -> new AppException(ErrorCode.NOT_FOUND_VILLAGE_ID.getKey(), ErrorCode.NOT_FOUND_VILLAGE_ID.getValue() + userRequet.getVillage_id()));
        user.setRole(role);
        user.setVillage(village);
        user.setUsername(userRequet.getUsername());
        user.setPassword(passwordEncoder.encode(userRequet.getPassword()));
        user.setAddress(userRequet.getAddress());
        user.setEmail(userRequet.getEmail());
        Date date = new SimpleDateFormat(Contants.DATE_FORMAT).parse(userRequet.getBirthOfdate());
        user.setBirthOfdate(date);
        user.setFirstname(userRequet.getFirstname());
        user.setLastname(userRequet.getLastname());
        user.setPhone(userRequet.getPhone());
        user.setImageUrl(userRequet.getImageUrl());
        user.setModifiedDate(new Date());
        UserRequet userRequet1 = userConvert.convertToUserRequest(userRepository.save(user));
        return userRequet1;
    }

    @Override
    public List<UserRequet> searchByRole( Long role_id, Integer page) {
        if(page == null){
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contants.PAGE_SIZE);
        List<User> searchList = userRepository.findAllUserByRoleId(role_id, pageable);
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
        List<User> searchList = userRepository.findByRole(role);
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
        Pageable pageable = PageRequest.of(page, Contants.PAGE_SIZE);
        List<User> searchList = userRepository.findByUsernameContaining(text, pageable);
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
    public Page<User> getAllUserByPage(Integer page) {
        List<UserRequet> userRequets = new ArrayList<>();
        if(page == null){
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Contants.PAGE_SIZE);
        Page<User> searchList = userRepository.findAll(pageable);
        return searchList;
    }

    @Override
    public User login(String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(username);
        if (passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }


}
