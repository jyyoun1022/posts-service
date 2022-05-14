package com.codeJ.posts.service.repository;

import com.codeJ.posts.service.entity.Role;
import com.codeJ.posts.service.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

//    @AfterEach
//    public void clear(){
//        userRepository.deleteAll();
//    }

    @Test
    @DisplayName("유저 생성")
    void createUsers(){
        String username = "codeJ";
        String rawPassword = "123!@#qwe";
        String enPassword = encoder.encode(rawPassword);

        userRepository.save(User.builder().username(username).password(enPassword).nickname("코드제이").email("jyyoun1022@gmail.com").role(Role.USER).build());

        List<User> userList = userRepository.findAll();

        User user = userList.get(0);
        System.out.println(user);
        assertThat(user.getUsername()).isEqualTo(username);
        assertThat(user.getPassword()).isEqualTo(enPassword);
    }
}
