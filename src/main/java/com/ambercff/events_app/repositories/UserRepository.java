package com.ambercff.events_app.repositories;

import com.ambercff.events_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByEmail(String email);
    Optional<UserDetails> findByEmail(String email);
}

