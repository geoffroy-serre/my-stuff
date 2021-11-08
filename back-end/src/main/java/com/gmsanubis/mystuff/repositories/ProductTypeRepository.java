package com.gmsanubis.mystuff.repositories;

import com.gmsanubis.mystuff.models.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductTypeRepository extends MongoRepository<ProductType,String> {
}
