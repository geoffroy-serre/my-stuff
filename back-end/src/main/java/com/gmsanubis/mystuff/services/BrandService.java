package com.gmsanubis.mystuff.services;

import com.gmsanubis.mystuff.models.Brand;
import java.util.List;

public interface BrandService {
  List<Brand> getBrands();

  Brand getBrand(String id);

  void updateBrand(Brand brand);

  void addBrand(Brand brand);

  void deleteBrand(String id);
}
