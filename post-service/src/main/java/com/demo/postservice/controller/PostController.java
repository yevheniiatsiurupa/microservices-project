package com.demo.postservice.controller;

import com.demo.common.utils.InteractionService;
import com.demo.common.utils.ServiceInfo;
import com.demo.postservice.entity.Post;
import com.demo.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService commentService;
    private final InteractionService interactionService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping(value = "/interact")
    public ResponseEntity<String> testInteraction(HttpServletRequest request) {
        ResponseEntity<String> response = interactionService.execute(
                ServiceInfo.COMMENT_SERVICE, "/comments/interact", HttpMethod.GET, request, null, String.class);
        return response;
    }
}
