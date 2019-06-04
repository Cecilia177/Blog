package com.cecilia.blog.controller;

import com.cecilia.blog.entity.Article;
import com.cecilia.blog.entity.Comment;
import com.cecilia.blog.repository.ArticleRepository;
import com.cecilia.blog.repository.CommentRepositoty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    CommentRepositoty commentRepositoty;


    @ApiOperation(value = "获取所有评论")
    @GetMapping
    public Iterable<Comment> getAllComments(){
        return commentRepositoty.findAll();
    }

    @ApiOperation(value = "获取某个评论", notes = "根据某个评论号获取评论")
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId){
        Optional<Comment> optionalComment = commentRepositoty.findById(commentId);
        if(optionalComment.isPresent()){
            return new ResponseEntity<>(optionalComment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "增加评论")
    @PostMapping(consumes = "application/json")
    public Comment postComment(@RequestBody Comment comment){
        return commentRepositoty.save(comment);
    }

    @ApiOperation(value = "删除评论", notes = "根据评论号删除某条评论")
    @DeleteMapping("/{commentId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("commentId") Long commentId){
        try{
            commentRepositoty.deleteById(commentId);
        } catch (EmptyResultDataAccessException e) {}
    }

    @ApiOperation(value = "更新评论", notes = "根据评论号更新某条评论")
    @PatchMapping(path = "/{commentId}", consumes = "application/json")
    public Comment patchComment(@PathVariable("commentId") Long commentId, @RequestBody Comment commentToPatch){
        Comment comment = commentRepositoty.findById(commentId).get();
        if(commentToPatch.getContent() != null){
            comment.setContent(commentToPatch.getContent());
        }
        if(commentToPatch.getIsValid() != null){
            comment.setIsValid(commentToPatch.getIsValid());
        }

        return commentRepositoty.save(comment);
    }


}
