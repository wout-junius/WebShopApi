package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.Tag;
import com.ehb.webshopapi.repositories.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "tag")
@Slf4j
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> tagsList(){
        ArrayList<Tag> tags = new ArrayList<>();
        tagRepository.findAll().forEach(tags::add);
        return tags;
    }

    @PostMapping
    public Tag addTag(@RequestBody Tag tag){
        return tagRepository.save(tag);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Tag getTagById(@PathVariable long id){
        return tagRepository.findById(id).get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Tag updateTag(@PathVariable long id, @RequestBody Tag tag){
        Tag existingTag = tagRepository.findById(id).get();
        BeanUtils.copyProperties(tag,existingTag, "id");
        return tagRepository.save(existingTag);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable long id){
        tagRepository.deleteById(id);
    }
}
