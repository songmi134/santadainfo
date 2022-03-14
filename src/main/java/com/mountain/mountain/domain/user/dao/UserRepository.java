package com.mountain.mountain.domain.user.dao;

import com.mountain.mountain.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
