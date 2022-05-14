package com.codeJ.posts.service.security.auth;

import com.codeJ.posts.service.dto.UserDTO;
import com.codeJ.posts.service.entity.User;
import com.codeJ.posts.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    //username이 DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 존재하지 않습니다. " + username));
        httpSession.setAttribute("user",new UserDTO.Response(user));
        //시큐리티 세션에 유저 정보 저장
        return new CustomUserDetails(user);
    }
}
