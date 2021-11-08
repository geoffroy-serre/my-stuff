package com.gmsanubis.mystuff.services;

import com.gmsanubis.mystuff.exceptions.AlreadyExistException;
import com.gmsanubis.mystuff.exceptions.NotFoundException;
import com.gmsanubis.mystuff.models.Paint;
import com.gmsanubis.mystuff.repositories.PaintRepository;
import com.gmsanubis.mystuff.utils.StringFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaintServiceImpl implements PaintService {

  Logger logger = LoggerFactory.getLogger(PaintServiceImpl.class);

  @Autowired
  PaintRepository paintRepository;

  @Override
  public List<Paint> getPaints() {
    List<Paint> paints = paintRepository.findAll();
    if (!paints.isEmpty()) {
      logger.info("getPaints is not empty");
      paints.forEach(StringFormatter::capitalizeFirstAll);
    }
    //TODO: return empty ou throw notfound???
    return paints;
  }

  @Override
  public void addPaint(Paint paint) {
    StringFormatter.lowerCaseAll(paint);
    if (paintRepository.findPaintByBrandIdAndName(paint.getBrandId(), paint.getName()) != null) {
      throw new AlreadyExistException("paint", paint.getBrandId(), paint.getName());
    }
    logger.info("Paint doesn't exist, saving to db");
    paintRepository.save(paint);
  }

  @Override
  public Paint getById(String id) {
    Paint paint = paintRepository.findPaintById(id);
    logger.info("Looking for Paint with id {}", id);
    if (paint != null) {
      return StringFormatter.capitalizeFirstAll(paint);
    }
    throw new NotFoundException("paint", id);
  }

  @Override
  public List<Paint> getByBrand(String brand) {
    logger.info("Getting paints for brand: {}", brand);

    List<Paint> paints = paintRepository.findPaintsByBrandId(brand.toLowerCase());
    if (paints.isEmpty()) {
      throw new NotFoundException("brand", brand);
    }
    paints.forEach(StringFormatter::capitalizeFirstAll);
    return paints;
  }

  @Override
  public void deletePaint(String id) {
    if (paintRepository.findPaintById(id) != null) {
      paintRepository.deleteById(id);
    } else {
      throw new NotFoundException("paint", id);
    }

  }

  @Override
  public void updatePaint(Paint paint) {
    StringFormatter.lowerCaseAll(paint);
    if (paintRepository.findPaintById(paint.getId()) != null) {
      paintRepository.save(paint);
    } else {
      throw new NotFoundException("paint", paint.getId());
    }
  }

  @Override
  public List<Paint> getByColor(String color) {
    List<Paint> paints = paintRepository.findPaintsByColor(color);
    if (!paints.isEmpty()) {
      logger.info("Paints found with color: " + color);
      paints.forEach(StringFormatter::capitalizeFirstAll);
    }
    return paints;
  }

  @Override
  public List<Paint> getByType(String type) {
    List<Paint> paints = paintRepository.findPaintsByType(type);
    if (!paints.isEmpty()) {
      logger.info("Paints found with type " + type);
      paints.forEach(StringFormatter::capitalizeFirstAll);
    }
    return paints;
  }

  @Override
  public Paint getByName(String name) {
    Paint paint = paintRepository.findPaintByName(name);
    if (paint != null) {
      logger.info("Pain found with name: " + name);
      return StringFormatter.capitalizeFirstAll(paint);
    }
    throw new NotFoundException("paint", name);
  }
}
