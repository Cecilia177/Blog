package com.cecilia.blog.service.impl;

import com.cecilia.blog.entity.Article;
import com.cecilia.blog.repository.ArticleRepository;
import com.cecilia.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Iterable<Article> getTopArticles() {
        List<Article> articles = articleRepository.getValidArticles();
        List<Article> topArticles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            topArticles.add(articles.get(articles.size() - 1 - i));
        }
        return topArticles;
    }
}
