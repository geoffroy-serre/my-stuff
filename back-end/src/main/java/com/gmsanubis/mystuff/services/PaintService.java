package com.gmsanubis.mystuff.services;

import com.gmsanubis.mystuff.models.Paint;
import java.util.List;
import org.springframework.data.domain.Page;

public interface PaintService {

  /**
   * Return a list of paints.<br>
   * All String attributes have the first letter capitalized before return.
   *
   * @return List of Paint
   * @see com.gmsanubis.mystuff.models.Paint Paint
   * @see com.gmsanubis.mystuff.utils.StringFormatter#capitalizeFirstAll(com.gmsanubis.mystuff.models.Paint) StringFormatter.capitalizeFirstAll(paint)
   */
  List<Paint> getPaints();

  /**
   * Add Paint after set String attributes to lower case.<br>
   * Throw a new AlreadyExistException if give paint's name and brand is already in DB.
   *
   * @param paint <strong>Paint</strong>
   * @see com.gmsanubis.mystuff.exceptions.AlreadyExistException Already Exist Exception
   * @see com.gmsanubis.mystuff.exceptions.ExceptionHandling#handleAlreadyExistException(com.gmsanubis.mystuff.exceptions.AlreadyExistException, javax.servlet.http.HttpServletRequest) handle already exist exception
   * @see com.gmsanubis.mystuff.models.Paint Paint
   * @see com.gmsanubis.mystuff.utils.StringFormatter#lowerCaseAll(com.gmsanubis.mystuff.models.Paint) StringFormatter.lowerCaseAll(paint)
   */
  void addPaint(Paint paint);

  /**
   * First letter of String attributes is capitalized before return.<br>
   * Throw new not found exception if no match.
   *
   * @param id <strong>String</strong>
   * @return a Paint object
   * @see com.gmsanubis.mystuff.models.Paint Paint
   * @see com.gmsanubis.mystuff.utils.StringFormatter#capitalizeFirstAll(com.gmsanubis.mystuff.models.Paint) StringFormatter.capitalizeFirstAll(paint)
   * @see com.gmsanubis.mystuff.exceptions.NotFoundException Not Found Exception
   * @see com.gmsanubis.mystuff.exceptions.ExceptionHandling#handleNotFoundException(com.gmsanubis.mystuff.exceptions.NotFoundException, javax.servlet.http.HttpServletRequest) handle not found exception
   */
  Paint getById(String id);

  /**
   * Retrieve all Paint made by the brand given in parameters.<br>
   * All Paints in list have the first letter of their String attribute capitalized.
   *
   * @param brand <strong>String</strong>
   * @return List of Paint
   * @see com.gmsanubis.mystuff.models.Paint Paint
   * @see com.gmsanubis.mystuff.utils.StringFormatter#capitalizeFirstAll(com.gmsanubis.mystuff.models.Paint) StringFormatter.capitalizeFirstAll(paint)
   * @see com.gmsanubis.mystuff.exceptions.NotFoundException Not Found Exception
   * @see com.gmsanubis.mystuff.exceptions.ExceptionHandling#handleNotFoundException(com.gmsanubis.mystuff.exceptions.NotFoundException, javax.servlet.http.HttpServletRequest) handle not found exception
   */
  List<Paint> getByBrand(String brand);

  /**
   * Delete in database the Paint matching the given id in param.<br>
   * If no match send new NotFoundException
   *
   * @param id <strong>int</strong>
   * @see com.gmsanubis.mystuff.exceptions.NotFoundException Not Found Exception
   * @see com.gmsanubis.mystuff.exceptions.ExceptionHandling#handleNotFoundException(com.gmsanubis.mystuff.exceptions.NotFoundException, javax.servlet.http.HttpServletRequest) handle not found exception
   */
  void deletePaint(String id);

  /**
   * Update Paint.<br>
   * Check if paint id already exist before update if not throw a new Not Found Exception.
   *
   * @param paint <strong>Paint</strong>
   * @see com.gmsanubis.mystuff.models.Paint Paint
   * @see com.gmsanubis.mystuff.exceptions.NotFoundException No Found Exception
   * @see com.gmsanubis.mystuff.exceptions.ExceptionHandling#handleNotFoundException(com.gmsanubis.mystuff.exceptions.NotFoundException, javax.servlet.http.HttpServletRequest) Handle not found exception  Exception Handling
   */
  void updatePaint(Paint paint);

  /**
   * Retrieve all paint with given color.<br>
   * Capitalize all result before return.
   *
   * @param color      <string>String</string>
   * @return List of paint
   * @see com.gmsanubis.mystuff.models.Paint Paint
   * @see com.gmsanubis.mystuff.utils.StringFormatter#capitalizeFirstAll(com.gmsanubis.mystuff.models.Paint) StringFormatter.capitalizeFirstAll(paint)
   */
  List<Paint> getByColor(String color);

  List<Paint> getByType(String type);

  Paint getByName(String name);
}
