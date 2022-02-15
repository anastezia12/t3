package com.codejava.codejavaapp.repos;

import com.codejava.codejavaapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.nickName = ?1")
    public User findByNickName(String nickName);

}

