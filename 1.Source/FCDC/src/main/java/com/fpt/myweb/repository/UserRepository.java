package com.fpt.myweb.repository;

import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.Role;
import com.fpt.myweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUsersByPhone(String phone);

    @Query(value = "select u from User u where u.roles ?1 limit ?2 offset ?3")
    List<User> findByRoles(Role role, Integer limit, Integer offset);

    List<User> findByRoles(Role role);

    List<User> findByUsernameContaining(String text);

    @Query(value = "select u from User u where u.username like ?1 limit ?2 offset ?3")
    List<User> findByUsernameContaining(String text, Integer limit, Integer offset);

    User findByUsernameAndPassword(String username , String pass);

    @Query(value = "select u from User u limit ?1 offset ?2")
    public List<UserRequet> getAllUserByPage(Integer limit, Integer offset);



}
