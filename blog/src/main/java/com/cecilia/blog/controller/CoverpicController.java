package com.cecilia.blog.controller;

import com.cecilia.blog.entity.Article;
import com.cecilia.blog.entity.Coverpic;
import com.cecilia.blog.repository.CoverpicRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/coverpic", produces = "application/json")
@CrossOrigin(origins="*")
public class CoverpicController {
    @Autowired
    CoverpicRepository coverpicRepository;

    @ApiOperation(value = "创建图片", notes = "")
    @PostMapping
    public Coverpic putCoverpic(@RequestBody Coverpic coverpic){
        coverpicRepository.save(coverpic);
        return coverpic;
    }

    @ApiOperation(value = "获取图片", notes = "根据图片id获取一张图片")
    @GetMapping("/{id}")
    public Coverpic coverpicById(@PathVariable Long id){
        Coverpic coverpic = new Coverpic();

        Optional<Coverpic> optCoverpic = coverpicRepository.findById(id);
        if(optCoverpic.isPresent()){
            return optCoverpic.get();
        }
        return null;
    }
}
