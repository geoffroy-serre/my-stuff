package com.gmsanubis.mystuff.controllers;

import com.gmsanubis.mystuff.models.Brand;
import com.gmsanubis.mystuff.services.BrandService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/brands")
public class BrandController {

  Logger logger = LoggerFactory.getLogger(BrandController.class);

  @Autowired
  BrandService brandService;

  @GetMapping
  public List<Brand> getBrands(){
    logger.info("Entering getBrands");
    return brandService.getBrands();
  }

  @GetMapping("/{id}")
  public Brand getBrand(@PathVariable String id){
    logger.info("Entering getBrand with id {}",id);
    return brandService.getBrand(id);
  }

  @PutMapping("/update")
  public void updateBrand(@RequestBody @Valid Brand brand){
    logger.info("Entering updateBrand for brand's id {}",brand.getId());
    brandService.updateBrand(brand);
  }

  @PostMapping("/add")
  public void addBrand(@RequestBody @Valid Brand brand){
    logger.info("Entering add brand for name {}",brand.getName());
    brandService.addBrand(brand);
  }

  @DeleteMapping("/{id}")
  public void deleteBrand(@PathVariable String id){
    logger.info("Entering deleteBrand for brand's id: {}",id);
    brandService.deleteBrand(id);
  }

}
