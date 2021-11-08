package com.gmsanubis.mystuff.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistException extends RuntimeException {

  private String type;
  private String brand;
  private String name;

  public AlreadyExistException(String type, String brand, String name){
    super();
    this.type=type;
    this.brand=brand;
    this.name=name;
  }
}
