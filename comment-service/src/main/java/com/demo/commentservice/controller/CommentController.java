package com.demo.commentservice.controller;

import com.demo.commentservice.entity.Comment;
import com.demo.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/comments")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping(value = "/interact")
    public ResponseEntity<String> testInteraction() {
        log.info("Inside comment controller");
        return ResponseEntity.ok("message from comments-service");
    }
}
