package com.cecilia.blog.controller.FrontController;


import com.cecilia.blog.entity.Article;
import com.cecilia.blog.service.ArticleService;
import com.cecilia.blog.service.impl.ArticleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/article", produces = "application/json")
@CrossOrigin(origins="*")
public class ArticleFrontController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/toplist")
    public Iterable<Article> getTopArticles() {
        return articleService.getTopArticles();
    }
}
