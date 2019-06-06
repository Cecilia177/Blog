package com.cecilia.blog.repository;

import com.cecilia.blog.entity.TaggedArticle;
import org.springframework.data.repository.CrudRepository;

public interface TaggedArticleRepository extends CrudRepository<TaggedArticle, Long> {

}
