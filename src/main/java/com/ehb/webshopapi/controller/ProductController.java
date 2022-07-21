package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.Product;
import com.ehb.webshopapi.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> productsList(){
        ArrayList<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
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
