package com.fpt.myweb.repository;

import com.fpt.myweb.dto.request.UserRequet;
import com.fpt.myweb.entity.New;
import com.fpt.myweb.entity.Role;
import com.fpt.myweb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUsersByPhone(String phone);

    List<User> findAllUserByRole(Role role, Pageable pageable);

    List<User> findByRole(Role role);

    List<User> findByUsernameContaining(String text);

    User findByUsername(String username);

}
