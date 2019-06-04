package com.cecilia.blog.repository;

import com.cecilia.blog.entity.Article;
import com.cecilia.blog.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Repository
public class CommentRepoImp {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Comment postComment(Comment comment) {
        long articleId = comment.getArticle().getId();
        log.info("articleid: " + articleId);
        entityManager.persist(comment);
        Article article = entityManager.find(Article.class, articleId);
        article.getComments().add(comment);
        entityManager.persist(article);
        return comment;
    }
}
