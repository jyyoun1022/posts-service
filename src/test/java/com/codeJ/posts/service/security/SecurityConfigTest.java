package com.codeJ.posts.service.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {

    @Test
    @DisplayName("해쉬 암호화")
    void testSecurity(){
        String encodePassword = new BCryptPasswordEncoder().encode("1234");

        System.out.println("1234 해쉬 : "+encodePassword);
    }
}
