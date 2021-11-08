package com.gmsanubis.mystuff.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

  private String typeOfProduct;
  private String requestedValue;

  public NotFoundException(String title, String requestedValue){
    super();
    this.typeOfProduct=title;
    this.requestedValue=requestedValue;
  }

}
