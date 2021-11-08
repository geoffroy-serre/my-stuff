package com.gmsanubis.mystuff.repositories;

import com.gmsanubis.mystuff.models.Paint;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaintRepository extends MongoRepository<Paint, String> {

  Paint findPaintById(String id);

  List<Paint> findPaintsByBrandId(String brand);

  Paint findPaintByBrandIdAndName(String brand, String name);

  List<Paint> findPaintsByColor(String color);

  List<Paint> findPaintsByType(String type);

  Paint findPaintByName(String name);
}
