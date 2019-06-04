package com.cecilia.blog.controller;

import com.cecilia.blog.entity.Article;
import com.cecilia.blog.entity.Category;
import com.cecilia.blog.repository.CategoryRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @ApiOperation(value = "获取所有分类")
    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @ApiOperation(value = "获取某个分类", notes = "根据类别号获取分类")
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            return new ResponseEntity<>(optionalCategory.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "新建分类")
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Category postCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @ApiOperation(value = "删除分类", notes = "根据类别号删除某个分类")
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        try{
            categoryRepository.deleteById(categoryId);
        } catch (EmptyResultDataAccessException e){}
    }

    @ApiOperation(value = "更新分类", notes = "根据类别号更新某个类名")
    @PatchMapping(path = "/{categoryId}", consumes = "application/json")
    public Category patchCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category categoryToPatch){
        Category category = categoryRepository.findById(categoryId).get();
        if(categoryToPatch.getCategoryName() != null){
            category.setCategoryName(categoryToPatch.getCategoryName());
        }
        return categoryRepository.save(category);
    }

    @ApiOperation(value = "获取文章列表", notes = "获取某一类别下所有文章列表")
    @GetMapping(path = "/getArticles/{categoryId}")
    public Iterable<Article> getArticlesByCategory(@PathVariable("categoryId") Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return category.getArticles();
        }
        return null;
    }
}
