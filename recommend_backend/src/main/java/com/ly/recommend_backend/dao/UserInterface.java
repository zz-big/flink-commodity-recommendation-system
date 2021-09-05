package com.ly.recommend_backend.dao;

import com.ly.recommend_backend.entity.ProductEntityP;
import com.ly.recommend_backend.entity.UserP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInterface extends JpaRepository<UserP, String>, JpaSpecificationExecutor<UserP> {
    UserP getUserById(Integer id);
    UserP getUserByName(String name);
    UserP save(UserP user);
}
