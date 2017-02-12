package com.epamUniversity.dao;

import com.epamUniversity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andriy_Yarish on 1/23/2017.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
