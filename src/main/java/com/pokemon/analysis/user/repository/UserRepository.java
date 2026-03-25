package com.pokemon.analysis.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.analysis.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


    void deleteByUsernameAndPassword(String username, String password);
    void deleteByEmailAndPassword(String email, String passsword);

    User findByUsername(String username);


}
