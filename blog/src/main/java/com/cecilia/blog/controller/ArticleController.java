package com.cecilia.blog.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.cecilia.blog.entity.Comment;
import com.cecilia.blog.entity.Tag;
import com.cecilia.blog.entity.TaggedArticle;
import com.cecilia.blog.repository.ArticleRepoImp;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cecilia.blog.entity.Article;
import com.cecilia.blog.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/article", produces = "application/json")
@CrossOrigin(origins="*")
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleRepoImp articleRepoImp;

	@ApiOperation(value = "获取一篇文章", notes = "根据文章id获取文章")
	@GetMapping("/{id}")
	public ResponseEntity<Article> articleById(@PathVariable("id") Long id) {
		Optional<Article> optArticle = articleRepository.findById(id);
		if(optArticle.isPresent()) {
			return new ResponseEntity<>(optArticle.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "获取所有文章")
	@GetMapping
	public Iterable<Article> articleAll(){
		return articleRepository.findAll();
	}

	@ApiOperation(value = "创建文章")
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void articlePost(@RequestBody Article article){
		articleRepository.save(article);
//		Article persistentArticle = articleRepository.findById(article.getId()).get();
//		persistentArticle.getCategory().getArticles().add(persistentArticle);
		//return persistentArticle;
	}

	@ApiOperation(value = "获取标签", notes = "获取谋篇文章的标签")
	@GetMapping("/getTags/{articleId}")
	public Iterable<Tag> getTagsByArticleId(@PathVariable("articleId") Long articleId) {
		Optional<Article> optionalArticle = articleRepository.findById(articleId);
		if(optionalArticle.isPresent()) {
			Article article = optionalArticle.get();

			Set<TaggedArticle> taggedArticleSet =  article.getTaggedArticles();
			Set<Tag> tags = new HashSet<>();
			for (TaggedArticle taggedArticle: taggedArticleSet) {
				tags.add(taggedArticle.getTag());
			}
			return tags;
		}
		return null;
	}


	@ApiOperation(value = "查找评论", notes = "查找某篇文章下的所有评论")
	@GetMapping("/getComments/{articleId}")
	public Iterable<Comment> getCommentsByArticleId(@PathVariable("articleId") Long articleId){
		Optional<Article> optionalArticle = articleRepository.findById(articleId);
		if(optionalArticle.isPresent()) {
			Article article = optionalArticle.get();
			return article.getComments();
		}
		return null;
	}

}
