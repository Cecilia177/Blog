package com.cecilia.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.cecilia.blog.entity.Article;

/**
 * Article repository
 * 
 * @author CeciliaYe
 * @since 2019/5/21 10:50
 *
 */
public interface ArticleRepository extends CrudRepository<Article, Long>{

}
