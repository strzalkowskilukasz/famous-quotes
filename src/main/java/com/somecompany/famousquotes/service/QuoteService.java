package com.somecompany.famousquotes.service;

import com.somecompany.famousquotes.common.exception.QuoteNotFoundException;
import com.somecompany.famousquotes.model.Quote;
import com.somecompany.famousquotes.repository.QuoteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteService {

  private final QuoteRepository quoteRepository;

  private final AuthorService authorService;

  public List<Quote> findAllQuotes() {
    return quoteRepository.findAll();
  }

  public Quote add(Quote newQuote) {
    return createAndSave(new Quote(), newQuote);
  }

  public Quote update(Quote newQuote, Long id) {
    Quote quote = getById(id);
    return createAndSave(quote, newQuote);
  }

  public Quote getById(Long id) {
    return quoteRepository.findById(id)
        .orElseThrow(() -> new QuoteNotFoundException(id));
  }

  @Synchronized
  public void deleteById(Long id) {
    try {
      var authorId = getById(id).getAuthor().getId();

      quoteRepository.deleteById(id);

      if (findAllQuotesByAuthorId(authorId).isEmpty()) {
        authorService.deleteById(authorId);
      }
    } catch (EmptyResultDataAccessException exception) {
      throw new QuoteNotFoundException(id);
    }
  }

  private Quote createAndSave(Quote quote, Quote newQuote) {
    var author = authorService.findOrSaveNewAuthor(newQuote.getAuthor());

    quote.setAuthor(author);
    quote.setContent(newQuote.getContent());

    return quoteRepository.save(quote);
  }

  private List<Quote> findAllQuotesByAuthorId(Long id) {
    return quoteRepository.findAllByAuthorId(id);
  }
}
