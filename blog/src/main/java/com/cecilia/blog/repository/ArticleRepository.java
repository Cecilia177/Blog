package com.cecilia.blog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cecilia.blog.entity.Article;

import java.util.List;

/**
 * Article repository
 * 
 * @author CeciliaYe
 * @since 2019/5/21 10:50
 *
 */
public interface ArticleRepository extends CrudRepository<Article, Long>{
    @Query("SELECT o FROM Article o WHERE o.isValid = '1'")
    List<Article> getValidArticles();
}
