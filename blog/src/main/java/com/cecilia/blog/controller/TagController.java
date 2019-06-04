package com.cecilia.blog.controller;


import com.cecilia.blog.entity.Tag;
import com.cecilia.blog.repository.TagRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tag")
@CrossOrigin("*")
public class TagController {
    @Autowired
    TagRepository tagRepository;

    @ApiOperation(value = "获取所有标签")
    @GetMapping
    public Iterable<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    @ApiOperation(value = "获取某个标签", notes = "根据标签号获取某个标签")
    @GetMapping("/{tagId}")
    public ResponseEntity<Tag> tagById(@PathVariable("tagId") Long id){
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if(optionalTag.isPresent()){
            return new ResponseEntity<>(optionalTag.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "创建标签")
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag postTag(@RequestBody Tag tag){
        return tagRepository.save(tag);

    }

    @ApiOperation(value = "删除标签", notes = "根据标签号删除某个标签")
    @DeleteMapping("/{tagId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable("tagId") Long id){
        try{
            tagRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){}
    }

    @ApiOperation(value = "更新标签", notes = "根据标签号更新某个标签的标签名")
    @PatchMapping(path = "/{tagId}", consumes = "application/json")
    public Tag patchTag(@PathVariable("tagId") Long tagId, @RequestBody Tag tagToPatch){
        Tag tag = tagRepository.findById(tagId).get();
        if(tagToPatch.getTagName() != null){
            tag.setTagName(tagToPatch.getTagName());
        }

        return tagRepository.save(tag);
    }
}
