package com.github.igorek2312.blog.app.repositories;

import com.github.igorek2312.blog.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByActivationKey(String activationKey);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByResetKey(String resetKey);

    @Query("update User u set u.firstName=:firstName,u.lastName=:lastName where u.username=:username")
    @Modifying
    void update(
            @Param("username") String username,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );

    @Query("update User u set u.email=:email where u.username=:username")
    @Modifying
    void update(@Param("username") String username, @Param("email") String email);

    @Query("update User u set u.imageUrl=:url where u.username=:username")
    @Modifying
    void updateImageUrl(@Param("url") String url, @Param("username") String username);

    @Query("select u.imageUrl from User u where u.username=:username")
    Optional<String> findImageUrlByUserName(@Param("username") String username);
}
