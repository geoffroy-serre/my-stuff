package com.gmsanubis.mystuff.services;

import com.gmsanubis.mystuff.exceptions.AlreadyExistException;
import com.gmsanubis.mystuff.exceptions.NotFoundException;
import com.gmsanubis.mystuff.models.Paint;
import com.gmsanubis.mystuff.repositories.PaintRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaintServiceImplTest {


  @Mock
  PaintRepository paintRepository;

  Paint paint;
  List<Paint> paints;

  @BeforeEach
  public void setUp() {
    paints = new ArrayList<>();
    paint = new Paint();
    paint.setName("Druchii Violet");
    paint.setType("Shade");
    paint.setBrandId("Fake ID");
    paint.setColor("Purple");
    paint.setImagePath("None yet");
  }

  @InjectMocks
  PaintService paintService = new PaintServiceImpl();


  @Test
  void getPaintsEmpty() {
    paintService.getPaints();
    verify(paintRepository, times(1)).findAll();
  }

  @Test
  void getPaintsWithContent() {
    paints.add(paint);
    when(paintRepository.findAll()).thenReturn(paints);
    paintService.getPaints();
    verify(paintRepository, times(1)).findAll();
  }

  @Test
  void addPaint() {
    paintService.addPaint(paint);
    verify(paintRepository, times(1)).save(paint);
    assertEquals("druchii violet", paint.getName());
    assertEquals("purple", paint.getColor());
    assertEquals("shade", paint.getType());
    assertEquals("None yet", paint.getImagePath());
  }

  @Test
  void addPaintAlreadyExist() {
    when(paintRepository.findPaintByBrandIdAndName(anyString(), anyString())).thenReturn(paint);
    assertThrows(AlreadyExistException.class, () -> paintService.addPaint(paint));
    verify(paintRepository, times(1)).findPaintByBrandIdAndName(paint.getBrandId(),
            paint.getName());

  }

  @Test
  void getById() {
    when(paintRepository.findPaintById(paint.getId())).thenReturn(paint);
    Paint result = paintService.getById(paint.getId());

    verify(paintRepository, times(1)).findPaintById(paint.getId());
    assertEquals("Druchii Violet", result.getName());
  }

  @Test
  void getByIdNotFoundException() {
    paint.setId("testId");
    when(paintRepository.findPaintById(paint.getId())).thenReturn(null);
    //paintService.getById(paint.getId());

    assertThrows(NotFoundException.class, () -> paintService.getById(paint.getId()));
    verify(paintRepository, times(1)).findPaintById(paint.getId());

  }

  @Test
  void getByBrand() {
    paints.add(paint);
    when(paintRepository.findPaintsByBrandId(anyString())).thenReturn(paints);
    paintService.getByBrand("test");
    verify(paintRepository, times(1)).findPaintsByBrandId(anyString());
  }

  @Test
  void getByBrandNotFoundException() {
    when(paintRepository.findPaintsByBrandId(anyString())).thenReturn(paints);
    assertThrows(NotFoundException.class, () -> paintService.getByBrand("test"));
    verify(paintRepository, times(1)).findPaintsByBrandId(anyString());
  }

  @Test
  void deletePaint() {
    when(paintRepository.findPaintById(anyString())).thenReturn(paint);
    assertDoesNotThrow(() -> paintService.deletePaint("id"));
    verify(paintRepository, times(1)).findPaintById(anyString());
  }

  @Test
  void deletePaintNotFoundException() {
    assertThrows(NotFoundException.class, () -> paintService.deletePaint("id"));
    verify(paintRepository, times(1)).findPaintById(anyString());
  }

  @Test
  void updatePaint() {
    paint.setId("testId");
    when(paintRepository.findPaintById("testId")).thenReturn(paint);
    assertDoesNotThrow(() -> paintService.updatePaint(paint));
    verify(paintRepository, times(1)).findPaintById(paint.getId());
    verify(paintRepository, times(1)).save(paint);
  }

  @Test
  void updatePaintNotFoundException() {
    paint.setId("testId");
    when(paintRepository.findPaintById("testId")).thenReturn(null);
    assertThrows(NotFoundException.class, () -> paintService.updatePaint(paint));
    verify(paintRepository, times(1)).findPaintById(paint.getId());
    verify(paintRepository, times(0)).save(paint);
  }

  @Test
  void getByColor() {
    paints.add(paint);
    when(paintRepository.findPaintsByColor(anyString())).thenReturn(paints);
    paintService.getByColor("color");
    verify(paintRepository, times(1)).findPaintsByColor("color");
  }

  @Test
  void getByColorEmpty() {
    assertDoesNotThrow(() -> paintService.getByColor("color"));
    verify(paintRepository, times(1)).findPaintsByColor("color");
  }

  @Test
  void getByType() {
    paints.add(paint);
    when(paintRepository.findPaintsByType(anyString())).thenReturn(paints);
    paintService.getByType("type");
    verify(paintRepository, times(1)).findPaintsByType("type");
  }

  @Test
  void getByTypeEmpty() {
    assertDoesNotThrow(() -> paintService.getByType("type"));
    verify(paintRepository, times(1)).findPaintsByType("type");
  }

  @Test
  void getByName() {
    when(paintRepository.findPaintByName(anyString())).thenReturn(paint);
    assertDoesNotThrow(() -> paintService.getByName("name"));
    verify(paintRepository, times(1)).findPaintByName(anyString());
  }

  @Test
  void getByNameNotFoundException() {
    assertThrows(NotFoundException.class, () -> paintService.getByName(paint.getName()));
    verify(paintRepository, times(1)).findPaintByName(paint.getName());
  }
}
