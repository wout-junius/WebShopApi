package com.ehb.webshopapi.repositories;

import com.ehb.webshopapi.models.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByName(String name);
    void deleteByName(String name);
}
