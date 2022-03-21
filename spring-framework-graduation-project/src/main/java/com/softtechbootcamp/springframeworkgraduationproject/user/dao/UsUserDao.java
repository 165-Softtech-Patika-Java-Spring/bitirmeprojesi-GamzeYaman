package com.softtechbootcamp.springframeworkgraduationproject.user.dao;

import com.softtechbootcamp.springframeworkgraduationproject.user.entity.UsUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsUserDao extends JpaRepository<UsUser, Long> {

    UsUser findByUsername(String username);
    Boolean existsByUsername(String username);
}
