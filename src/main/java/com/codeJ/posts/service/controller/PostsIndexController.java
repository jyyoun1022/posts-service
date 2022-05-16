package com.codeJ.posts.service.controller;

import com.codeJ.posts.service.dto.UserDTO;
import com.codeJ.posts.service.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class PostsIndexController {

    private final PostsService postsService;

//    @GetMapping("/")                    // default page = 0, size= 10
//    public String index(Model model, @PageableDefault(sort = "id",direction = Sort.Direction.DESC),
//                        Pageable pageable, @LoginUser UserDTO.Response user){
//
//    }
}
