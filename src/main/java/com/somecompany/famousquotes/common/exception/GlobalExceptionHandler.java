package com.somecompany.famousquotes.common.exception;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ExceptionsHelper helper;

  private final String QUOTE_CONFLICT_EXCEPTION_MESSAGE =
      "Cytat o podanej treści i autorze już istnieje!";

  @ResponseBody
  @ExceptionHandler(QuoteNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String quoteNotFoundHandler(QuoteNotFoundException exception) {
    return exception.getMessage();
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public String handleConstraintViolation() {
    return QUOTE_CONFLICT_EXCEPTION_MESSAGE;
  }

  @ResponseStatus(UNPROCESSABLE_ENTITY)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                      HttpServletRequest request) {
    return helper.getValidationErrorResponse(exception, request, UNPROCESSABLE_ENTITY);
  }
}
