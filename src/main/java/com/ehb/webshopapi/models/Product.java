package com.ehb.webshopapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Double price;

    @ManyToMany(mappedBy = "products")
    private Collection<Sale> sales;

    @ManyToMany(mappedBy = "products")
    private Collection<Tag> tags;
}
