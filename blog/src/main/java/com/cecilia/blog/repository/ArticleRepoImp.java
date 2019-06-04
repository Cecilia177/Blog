package com.cecilia.blog.repository;

import com.cecilia.blog.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Slf4j
@Repository
public class ArticleRepoImp {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Article postArticle(Article article) {
        entityManager.persist(article);
        log.info("article id: " + article.getId());
//        article.getCategory();
        Article persistentArticle = entityManager.find(Article.class, article.getId());
        return persistentArticle;
    }
}
