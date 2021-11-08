package com.gmsanubis.mystuff.controllers;


import com.gmsanubis.mystuff.models.Paint;
import com.gmsanubis.mystuff.services.PaintService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/paints")
public class PaintController {

  Logger logger = LoggerFactory.getLogger(PaintController.class);

  @Autowired
  PaintService paintService;

  // ------ Basic CRUD controllers ------

  @PostMapping("/add")
  @ResponseStatus(code = HttpStatus.CREATED)
  public void addPaint(@RequestBody @Valid Paint paint) {
    logger.info("Entering addPaint");
    paintService.addPaint(paint);
  }

  @GetMapping()
  public List<Paint> getPaints() {
    logger.info("Entering getPaints");
    return paintService.getPaints();
  }

  @GetMapping("/{id}")
  public Paint getPaint(@PathVariable String id) {
    logger.info("Entering getPaint with id {}", id);
    return paintService.getById(id);
  }

  @PutMapping("/update")
  public void updatePaint(@RequestBody @Valid Paint paint) {
    logger.info("Entering updatePaint with paint id {}", paint.getId());
    paintService.updatePaint(paint);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void deletePaint(@PathVariable String id) {
    logger.info("Entering deletePaint with id {}", id);
    paintService.deletePaint(id);
  }

  // ------ Other GET mappings ------

  @GetMapping("/brands/{brand}")
  public List<Paint> getByBrand(@PathVariable String brand) {
    logger.info("Entering getByBrand with brand {}", brand);
    return paintService.getByBrand(brand);
  }

  @GetMapping("/colors/{color}")
  public List<Paint> getByColor(@PathVariable String color) {
    logger.info("Entering getBycolor with color {}", color);
    return paintService.getByColor(color);
  }

  @GetMapping("/type/{type}")
  public List<Paint> getByType(@PathVariable String type) {
    logger.info("Entering getByType with type {}", type);
    return paintService.getByType(type);
  }

  @GetMapping("/name/{name}")
  public Paint getByName(@PathVariable String name) {
    logger.info("Entering getByName with name {}", name);
    return paintService.getByName(name);
  }

}
