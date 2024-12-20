package com.letshack.Hackathon.repository;

import com.letshack.Hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
