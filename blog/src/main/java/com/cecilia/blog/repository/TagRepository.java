package com.cecilia.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.cecilia.blog.entity.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

}
