package com.gmsanubis.mystuff.repositories;

import com.gmsanubis.mystuff.models.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand, java.lang.String> {
  Brand findBrandById(java.lang.String id);
}
