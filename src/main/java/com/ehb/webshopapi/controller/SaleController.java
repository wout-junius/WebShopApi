package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.Sale;
import com.ehb.webshopapi.repositories.SaleRepository;
import com.ehb.webshopapi.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "sale")
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userName}")
    public List<Sale> salesList(@PathVariable String userName){
        return new ArrayList<>(saleRepository.findAllByUser_Id(userRepository.findByUsername(userName).get().getId()).get());
    }

    @PostMapping("/save/{userName}")
    public Sale addSale(@RequestBody Sale sale, @PathVariable String userName){
        sale.setUser(userRepository.findByUsername(userName).get());
        sale.setDate(new Date());
        return saleRepository.save(sale);
    }
}
