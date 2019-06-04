package com.cecilia.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.cecilia.blog.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
