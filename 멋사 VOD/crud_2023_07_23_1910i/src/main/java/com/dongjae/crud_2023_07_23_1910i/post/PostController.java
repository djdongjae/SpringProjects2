package com.dongjae.crud_2023_07_23_1910i.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final List<PostDto> postList;

    public PostController() {
        postList = new ArrayList<>();
    }

    @PostMapping("create")
    public void createPost(@RequestBody PostDto postDto) {
        logger.info(postDto.toString());
        this.postList.add(postDto);
    }

    @GetMapping("read-all")
    public List<PostDto> readPostAll() {
        logger.info("in read all");
        return this.postList;
    }
}
