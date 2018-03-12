package com.warmcity.citygrants.controllers;

import java.util.AbstractMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GeneralController {

  private final static String DEFAULTFAILUREMESSAGE = "Сервіс недоступний, спробуйте пізніше";
  private final static String BADREQUESTMESSAGE = "Не вірний формат запиту";
  private final static String METHODNOTALLOWED = "Метод не підтримуєтсья";
  private final static String FALLBACKOPTION = "Помилка валідації";
  private final static String EXCEEDEDALLOWEDSIZE = "Помилка під час завантаження данних";
  private final static String ERRORMESSAGEKEY = "message";
  private final static int FIRST_FIELD_NOT_PASSED_VALIDATION = 0;

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map.Entry<String, String>> validationErrorHandler(MethodArgumentNotValidException ex) {

    FieldError error = ex.getBindingResult().getFieldErrors().get(FIRST_FIELD_NOT_PASSED_VALIDATION);
    Map.Entry<String, String> errorEntry = new AbstractMap.SimpleEntry<String, String>(ERRORMESSAGEKEY,
        messageSource.getMessage(error.getCode(), null, FALLBACKOPTION, null));
    return new ResponseEntity<Map.Entry<String, String>>(errorEntry, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MultipartException.class)
  public ResponseEntity<Map.Entry<String, String>> exceededSizeErrorHandler(MultipartException ex) {

    log.error("Error occured when trying to download files", ex);
    return new ResponseEntity<Map.Entry<String, String>>(
        new AbstractMap.SimpleEntry<String, String>(ERRORMESSAGEKEY, EXCEEDEDALLOWEDSIZE), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = METHODNOTALLOWED)
  public void errorMethodNotSupported(HttpRequestMethodNotSupportedException e) {

    log.error(e.getLocalizedMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = BADREQUESTMESSAGE)
  public void errorHttpMessageNotReadable(HttpMessageNotReadableException e) {

    log.error("Error in http request syntax", e);
    throw e;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = DEFAULTFAILUREMESSAGE)
  public void error500Default(Exception e) {

    log.error("Server error occurred", e);
    throw new RuntimeException(e.getMessage());
  }
}
