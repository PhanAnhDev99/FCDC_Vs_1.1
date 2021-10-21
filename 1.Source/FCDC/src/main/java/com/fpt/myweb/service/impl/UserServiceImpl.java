package com.fpt.myweb.service.impl;




import com.fpt.myweb.convert.UserConvert;
import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.Role;
import com.fpt.myweb.entity.User;
import com.fpt.myweb.entity.Village;
import com.fpt.myweb.exception.Dup;
import com.fpt.myweb.repository.RoleRepository;
import com.fpt.myweb.repository.UserRepository;
import com.fpt.myweb.repository.VillageRepository;
import com.fpt.myweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userRepository.findById(id).orElseThrow(() -> new Dup("Not found ID = " + id));
        UserRequet userRequet = userConvert.convertToUserRequest(user);
        return userRequet;
    }

    @Override
    public UserRequet addUser(UserRequet userRequet) {
        Role role = roleRepository.findById(userRequet.getRole_id()).orElseThrow(() -> new Dup("Not found role ID = " + userRequet.getRole_id()));
        Village village = villageRepository.findById(userRequet.getVillage_id()).orElseThrow(() -> new Dup("Not village ID = " + userRequet.getVillage_id()));
        User user = userConvert.convertToUser(userRequet);
        user.setRoles(role);
        user.setVillage(village);
        user.setCreatedDate(new Date());
        User user1 = userRepository.save(user);
        UserRequet userRequet1 = userConvert.convertToUserRequest(user);
        return userRequet1;
    }

    @Override
    public UserRequet deleteUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new Dup("Not found ID = " + id));
        UserRequet userRequet = userConvert.convertToUserRequest(user);
        userRepository.delete(user);
        return userRequet;
    }

    @Override
    public UserRequet edit(long id, UserRequet userRequet) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new Dup("Not found ID = " + id));
        Role role = roleRepository.findById(userRequet.getRole_id()).orElseThrow(() -> new Dup("Not found role ID = " + userRequet.getRole_id()));
        Village village = villageRepository.findById(userRequet.getVillage_id()).orElseThrow(() -> new Dup("Not village ID = " + userRequet.getVillage_id()));
        user1.setUsername(userRequet.getUsername());
        user1.setPassword(userRequet.getPassword());
        user1.setFirstname(userRequet.getFirstname());
        user1.setLastname(userRequet.getLastname());
        user1.setPhone(userRequet.getPhone());
        user1.setAddress(userRequet.getAddress());
        user1.setBirthOfdate(userRequet.getBirthOfdate());
        user1.setEmail(userRequet.getEmail());
        user1.setRoles(role);
        user1.setVillage(village);
        UserRequet userRequet1 = userConvert.convertToUserRequest(userRepository.save(user1));
        return userRequet1;
    }

    @Override
    public List<UserRequet> searchByRole( long role_id) {
        Role role = roleRepository.findById(role_id).orElseThrow(() -> new Dup("Not found role ID = " + role_id));
        List<User> searchList = userRepository.findByRoles(role);
        List<UserRequet> userRequets = new ArrayList<>();
        for (User user:searchList){
            userRequets.add(userConvert.convertToUserRequest(user));
        }
        return userRequets;
    }

    @Override
    public List<UserRequet> searchByTesxt(String text) {
        List<User> searchList = userRepository.findByUsernameContaining(text);
        List<UserRequet> userRequets = new ArrayList<>();
        for (User user:searchList){
            userRequets.add(userConvert.convertToUserRequest(user));
        }
        return userRequets;

    }


}
