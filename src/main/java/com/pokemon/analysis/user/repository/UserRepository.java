package com.pokemon.analysis.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemon.analysis.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User save(User user);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


    void deleteByUsernameAndPassword(String username, String password);
    void deleteByEmailAndPassword(String email, String passsword);

    Optional<User> findByUsername(String username);


}
