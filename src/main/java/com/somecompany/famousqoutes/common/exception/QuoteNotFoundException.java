package com.somecompany.famousqoutes.common.exception;

public class QuoteNotFoundException extends RuntimeException {

  public QuoteNotFoundException(Long id) {
    super(String.format("Nie znaleziono cytatu o id = %s", id));
  }
}
