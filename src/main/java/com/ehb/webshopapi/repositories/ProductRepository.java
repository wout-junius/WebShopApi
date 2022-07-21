package com.ehb.webshopapi.repositories;

import com.ehb.webshopapi.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
