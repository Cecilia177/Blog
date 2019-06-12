package com.cecilia.blog.service;

import com.cecilia.blog.entity.Article;

import java.util.Optional;

public interface ArticleService {
    //获取最新发表的前四篇文章
    Iterable<Article> getTopArticles();
}
