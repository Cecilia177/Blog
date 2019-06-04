package com.cecilia.blog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cecilia.blog.entity.Comment;

import java.util.List;

public interface CommentRepositoty extends CrudRepository<Comment, Long> {

//    @Query(value = "select o from Comment o where o.article = ?1")
//    List<Comment> findCommentsByArticleId(Long articleId);


}
