package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.Sale;
import com.ehb.webshopapi.repositories.SaleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "sale")
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;

    @GetMapping
    public List<Sale> salesList(){
        ArrayList<Sale> sales = new ArrayList<>();
        saleRepository.findAll().forEach(sales::add);
        return sales;
    }

    @PostMapping
    public Sale addSale(@RequestBody Sale sale){
        return saleRepository.save(sale);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Sale getSaleById(@PathVariable long id){
        return saleRepository.findById(id).get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Sale updateSale(@PathVariable long id, @RequestBody Sale sale){
        Sale existingSale = saleRepository.findById(id).get();
        BeanUtils.copyProperties(sale,existingSale, "id");
        return saleRepository.save(existingSale);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteSale(@PathVariable long id){
        saleRepository.deleteById(id);
    }
}
