package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.Product;
import com.ehb.webshopapi.models.Tag;
import com.ehb.webshopapi.repositories.ProductRepository;
import com.ehb.webshopapi.repositories.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Product> productsList(){
        ArrayList<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        Collection<Tag> tags = product.getTags();
        product.setTags(new ArrayList<>());
        tags.forEach(tag -> product.addTag(tagRepository.findByName(tag.getName())));
        return productRepository.save(product);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Product getProductById(@PathVariable long id){
        return productRepository.findById(id).get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable long id, @RequestBody Product product){
        Product existingProduct = productRepository.findById(id).get();
        BeanUtils.copyProperties(product,existingProduct, "id");
        return productRepository.save(existingProduct);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable long id){
        productRepository.deleteById(id);
    }


}
