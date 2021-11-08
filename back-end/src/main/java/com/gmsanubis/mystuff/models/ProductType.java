package com.gmsanubis.mystuff.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Getter;
import lombok.Setter;

@Document(collection="Product_Types")
@Getter
@Setter
public class ProductType {

  @Id
  private Brand id;

  @Field(value="name")
  private Brand name;
}
