package com.gmsanubis.mystuff.exceptions;


import com.gmsanubis.mystuff.models.ExceptionResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandling {
  private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

  @ExceptionHandler(NotFoundException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public ExceptionResponse handleNotFoundException(NotFoundException notfoundException,
                                                   HttpServletRequest request) {

    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 404,
            "No " + notfoundException.getTypeOfProduct() + " found for : " + notfoundException.getRequestedValue(),
            request.getRequestURI());
    logger.error("Not found Exception for this {} : {}", notfoundException.getTypeOfProduct(),
            notfoundException.getRequestedValue());
    return exceptionResponse;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleConstraintViolationException(ConstraintViolationException constraintViolationException,
                                                              HttpServletRequest request) {

    List<String> messages = constraintViolationException.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage).collect(Collectors.toList());
    String errorMessage = "";
    for (int i = 0; i < messages.size(); i++) {
      if (i < messages.size() - 1) {
        errorMessage += messages.get(i) + " and also ";
      } else {
        errorMessage += messages.get(i);
      }
    }
    ExceptionResponse response = new ExceptionResponse(new Date(), 400,
            errorMessage,
            request.getRequestURI());
    logger.error("Constraint violation :{} ", errorMessage);
    return response;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleConstraintViolationException(MethodArgumentNotValidException methodArgumentNotValidException,
                                                              HttpServletRequest request) {

    List<FieldError> messages = methodArgumentNotValidException.getFieldErrors();
    StringBuilder errorMessage = new StringBuilder();
    for (int i = 0; i < messages.size(); i++) {
      if (i < messages.size() - 1) {
        errorMessage.append(messages.get(i).getField()).append(' ').append(messages.get(i).getDefaultMessage()).append(" , ");
      } else {
        errorMessage.append(messages.get(i).getField()).append(' ').append(messages.get(i).getDefaultMessage());
      }
    }
    ExceptionResponse response = new ExceptionResponse(new Date(), 400,
            errorMessage.toString(),
            request.getRequestURI());
    logger.error("Method argument not Valid: {}", errorMessage);
    return response;
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleIllegalArgumentException(IllegalArgumentException illegalArgumentException,
                                                          HttpServletRequest request) {

    ExceptionResponse response = new ExceptionResponse(new Date(), 400,
            illegalArgumentException.getLocalizedMessage(),
            request.getRequestURI()
    );
    logger.error("Illegal Argument Exception: {}", illegalArgumentException.getLocalizedMessage());
    return response;
  }

  @ExceptionHandler(AlreadyExistException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.CONFLICT)
  public ExceptionResponse handleAlreadyExistException(AlreadyExistException alreadyExistException,
                                                       HttpServletRequest request) {

    String errorMessage = String.format("The %s %s with name %s already exist",
            alreadyExistException.getType(), alreadyExistException.getBrand(),
            alreadyExistException.getName());
    ExceptionResponse response = new ExceptionResponse(new Date(), 409,
            errorMessage,
            request.getRequestURI()
    );
    logger.error("Already Exist Exception: {}", errorMessage);
    return response;
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseBody
  @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
  public ExceptionResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
                                                                        HttpServletRequest request) {

    String errorMessage = String.format("The %s request is not supported for %s with %s",
            httpRequestMethodNotSupportedException.getMethod(), request.getRequestURI(),
            request.getQueryString());
    ExceptionResponse response = new ExceptionResponse(new Date(), 405,
            errorMessage,
            request.getRequestURI()
    );
    logger.error("Already Exist Exception: {}", errorMessage);
    return response;
  }
}
