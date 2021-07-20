package com.somecompany.famousqoutes.controller;

import com.somecompany.famousqoutes.dto.QuoteJson;
import com.somecompany.famousqoutes.dto.QuoteMapper;
import com.somecompany.famousqoutes.service.QuoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quote")
public class QuoteController {

  private final QuoteService quoteService;

  private final QuoteMapper quoteMapper;

  @GetMapping
  public ResponseEntity<List<QuoteJson>> findAll() {
    var quotes = quoteService.findAllQuotes();

    return new ResponseEntity<>(quoteMapper.mapToQuoteJsons(quotes), HttpStatus.OK);
  }
}
