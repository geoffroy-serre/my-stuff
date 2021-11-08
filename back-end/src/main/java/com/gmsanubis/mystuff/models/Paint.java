package com.gmsanubis.mystuff.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * A Paint is composed of these following attributes:
 * <ul>
 *   <li><strong>String</strong> id
 *   <p><i>Auto generated by MongoDB</i></p></li>
 *   <li><strong>String</strong> brandId
 *   <p><i>Can't be blank</i></p></li>
 *   <li><strong>String</strong> color
 *   <p><i>Can't be blank</i></p></li>
 *   <li><strong>String</strong> type
 *   <p><i>Can't be blank</i></p></li>
 * </ul>
 * This object is annotated as a MongoDb Document.
 *
 * @see org.springframework.data.mongodb.core.mapping.Document
 */
@Getter
@Setter
@Document(collection = "Paints")
public class Paint {

  @Id
  private String id;

  @NotBlank
  @Field(value = "brand")
  private String brandId;

  @NotBlank
  @Field(value = "color")
  private String color;

  @NotBlank
  @Field(value = "type")
  private String type;

  @NotBlank
  @Field(value="name")
  private String name;

  @Field(value="image")
  private String imagePath;


  @Override
  public String toString() {
    return "Paint{" +
            "id='" + id + '\'' +
            ", brand='" + brandId + '\'' +
            ", color='" + color + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
}