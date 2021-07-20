package com.somecompany.famousqoutes.service;

import com.somecompany.famousqoutes.model.Quote;
import com.somecompany.famousqoutes.repository.QuoteRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteService {

  private final QuoteRepository quoteRepository;

  private final AuthorService authorService;

  public List<Quote> findAllQuotes() {
    return quoteRepository.findAll();
  }

  public List<Quote> findAllQuotesByAuthorId(Long id) {
    return quoteRepository.findAllByAuthorId(id);
  }

  public Quote save(Quote quote) {
    var author = authorService.findOrSaveNewAuthor(quote.getAuthor());

    quote.setAuthor(author);

    return quoteRepository.save(quote);
  }
}
