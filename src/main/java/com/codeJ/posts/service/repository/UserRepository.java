package com.codeJ.posts.service.repository;

import com.codeJ.posts.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    /** Security */
    Optional<User> findByUsername(String username);

    /** OAuth */
    Optional<User> findByEmail(String email);

    /** user GET*/
    User findByNickname(String nickname);

    /** 중복검사 */
    boolean existByUsername(String username);
    boolean existByNickname(String nickname);
    boolean existByEmail(String email);
}
