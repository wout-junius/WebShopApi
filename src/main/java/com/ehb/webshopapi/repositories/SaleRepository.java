package com.ehb.webshopapi.repositories;

import com.ehb.webshopapi.models.Sale;
import com.ehb.webshopapi.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface SaleRepository extends CrudRepository<Sale, Long> {
    Optional<Collection<Sale>> findAllByUser_Id(Long user_id);
}
