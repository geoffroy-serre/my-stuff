package com.gmsanubis.mystuff.services;


import com.gmsanubis.mystuff.exceptions.AlreadyExistException;
import com.gmsanubis.mystuff.exceptions.NotFoundException;
import com.gmsanubis.mystuff.models.Brand;
import com.gmsanubis.mystuff.repositories.BrandRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

  Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

  @Autowired
  BrandRepository brandRepository;

  @Override
  public List<Brand> getBrands() {
    logger.info("Get all brands");
    return brandRepository.findAll();
  }

  @Override
  public Brand getBrand(String id) {
    Brand brand = brandRepository.findBrandById(id);
    if (brand != null) {
      logger.info("Brand found for id {}", id);
      return brand;
    } else {
      throw new NotFoundException("brand", id);
    }
  }

  @Override
  public void updateBrand(Brand brand) {
    if (brandRepository.findBrandById(brand.getId()) != null) {
      logger.info("Brand to update found");
      brandRepository.save(brand);
    } else {
      throw new NotFoundException("brand", brand.getId());
    }
  }

  @Override
  public void addBrand(Brand brand) {
    if (brandRepository.findBrandById(brand.getId()) == null) {
      logger.info("Brand {} doesn't exist and can be add",brand.getName());
      brandRepository.save(brand);
    } else {
      throw new AlreadyExistException("brand", brand.getId(), brand.getName());
    }
  }

  @Override
  public void deleteBrand(String id) {
    if (brandRepository.findBrandById(id) != null) {
      logger.info("Brand with id {} found proceed to delete",id);
      brandRepository.deleteById(id);
    } else {
      throw new NotFoundException("brand", id);
    }
  }
}
