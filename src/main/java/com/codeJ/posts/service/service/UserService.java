package com.codeJ.posts.service.service;

import com.codeJ.posts.service.dto.UserDTO;
import com.codeJ.posts.service.entity.User;
import com.codeJ.posts.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    /** 회원 가입 시 , 유효성 검사 및 중복 체크*/
    @Transactional(readOnly = true)
    public Map<String,String> validateHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();

        /** 유효성 검사, 중복 검사에 실패한 필드 목록을 받음*/
            for(FieldError error : errors.getFieldErrors()){
                String validKeyName = String.format("valid_%s", error.getField());
                validatorResult.put(validKeyName,error.getDefaultMessage());
            }
            return validatorResult;
    }

    /** 회원수정 (dirty checking) */
    @Transactional
    public Long modify(UserDTO.Request dto){
        User user = userRepository.findById(dto.dtoToEntity().getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String enPassword = encoder.encode(dto.getPassword());
        user.modify(dto.getNickname(),enPassword);

        return user.getId();
    }
}
