package com.cecilia.blog.controller;

import com.cecilia.blog.entity.TaggedArticle;
import com.cecilia.blog.repository.TaggedArticleRepository;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.Optional;


@RestController
@RequestMapping("/taggedArticle")
@CrossOrigin("*")
public class TaggedArticleController {

    @Autowired
    TaggedArticleRepository taggedArticleRepository;

    @ApiOperation("获取所有标签过的文章")
    @GetMapping
    public Iterable<TaggedArticle> getAllTaggedArticles() {
        return taggedArticleRepository.findAll();
    }

    @ApiOperation("获取某篇标签过的文章")
    @GetMapping("/{taggedArticleId}")
    public ResponseEntity<TaggedArticle> getTaggedArticle(@PathVariable("taggedArticleId") Long taggedArticleId) {
        Optional<TaggedArticle> optionalTaggedArticle = taggedArticleRepository.findById(taggedArticleId);
        if(optionalTaggedArticle.isPresent()) {
            return new ResponseEntity<>(optionalTaggedArticle.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation("删除某篇标签过的文章")
    @DeleteMapping("/{taggedArticleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaggedArticle(@PathVariable("taggedArticleId") Long taggedArticleId) {
        try {
            taggedArticleRepository.deleteById(taggedArticleId);
        } catch (EmptyResultDataAccessException e) {}
    }

    @ApiOperation("新增一篇标签过的文章")
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TaggedArticle postTaggedArticle(@RequestBody TaggedArticle taggedArticle) {
        return taggedArticleRepository.save(taggedArticle);
    }


}
