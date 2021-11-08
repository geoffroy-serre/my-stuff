package com.gmsanubis.mystuff.utils;

import com.gmsanubis.mystuff.models.Paint;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

/**
 * Contains custom method to format these following models:
 * <ul>
 *   <li>Paint</li>
 * </ul>
 *
 * @see Paint
 * @see org.apache.commons.lang3.StringUtils
 */
public class StringFormatter {

  /**
   * Capitalize the first letter of String attributes of Paint.<br>
   * Use Apache Commons Lang3.
   * @param paint Paint
   * @return Paint with first letter of attributes capitalized
   *
   * @see Paint
   * @see org.apache.commons.lang3.StringUtils
   */
  public static Paint capitalizeFirstAll(Paint paint){
    paint.setColor(StringUtils.capitalize(paint.getColor()));
    paint.setType(StringUtils.capitalize(paint.getType()));
    paint.setName(StringUtils.capitalize(paint.getName()));
    return paint;
  }

  /**
   * Set to lower case all letters of String attributes of Paint.
   * @param paint Paint
   * @return Paint with first letter of attributes capitalized
   *
   * @see Paint
   */
  public static Paint lowerCaseAll(Paint paint){
    paint.setColor(paint.getColor().toLowerCase());
    paint.setType(paint.getType().toLowerCase());
    paint.setName(paint.getName().toLowerCase());
    return paint;
  }
}
