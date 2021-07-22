package com.somecompany.famousquotes.service;

import com.somecompany.famousquotes.common.exception.QuoteNotFoundException;
import com.somecompany.famousquotes.model.Author;
import com.somecompany.famousquotes.model.Quote;
import com.somecompany.famousquotes.repository.QuoteRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    private QuoteService quoteService;

    @Mock
    private AuthorService authorService;

    private Author author;

    private Author newAuthor;

    private Quote quote;

    private Quote newQuote;

    @Mock
    private QuoteRepository quoteRepository;

    private Quote secondQuote;

    @BeforeEach
    void setup() {
        quoteService = new QuoteService(quoteRepository, authorService);

        newAuthor = Author.builder()
                .firstName("Joey")
                .lastName("Tribbiani")
                .build();

        author = Author.builder()
                .firstName("Joey")
                .lastName("Tribbiani")
                .id(1L)
                .build();

        newQuote = Quote.builder()
                .author(newAuthor)
                .content("Joey doesn't share food!")
                .build();

        quote = Quote.builder()
                .author(author)
                .content("Joey doesn't share food!")
                .id(1L)
                .build();

        secondQuote = Quote.builder()
                .author(author)
                .content("I don't like people take food from my plate, okay?")
                .id(2L)
                .build();
    }

    @Test
    @DisplayName("should save new quote")
    void save() {
        when(authorService.findOrSaveNewAuthor(newAuthor)).thenReturn(author);

        quoteService.add(newQuote);

        ArgumentCaptor<Quote> captor = ArgumentCaptor.forClass(Quote.class);
        verify(quoteRepository).save(captor.capture());

        assertThat(captor.getValue().getAuthor())
                .usingRecursiveComparison()
                .isEqualTo(author);

        assertThat(captor.getValue().getContent()).isEqualTo("Joey doesn't share food!");

        verify(authorService, times(1)).findOrSaveNewAuthor(newAuthor);
    }

    @Test
    @DisplayName("should update existing quote")
    void update() {
        var updatedQuote = Quote.builder()
                .author(author)
                .content("I don't like people take food from my plate, okay?")
                .build();

        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));
        when(authorService.findOrSaveNewAuthor(author)).thenReturn(author);

        quoteService.update(updatedQuote, 1L);

        ArgumentCaptor<Quote> captor = ArgumentCaptor.forClass(Quote.class);
        verify(quoteRepository, times(1)).save(captor.capture());

        assertThat(captor.getValue().getAuthor())
                .usingRecursiveComparison()
                .isEqualTo(author);

        assertThat(captor.getValue().getContent())
                .isEqualTo("I don't like people take food from my plate, okay?");

        verify(quoteRepository, times(1)).findById(1L);
        verify(authorService, times(1)).findOrSaveNewAuthor(author);
    }

    @Test
    @DisplayName("should find quote by id")
    void getById() {

        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));

        assertThat(quoteService.getById(1L))
                .usingRecursiveComparison()
                .isEqualTo(quote);

        verify(quoteRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("should find all quotes")
    void findAllQuotes() {
        when(quoteRepository.findAll()).thenReturn(List.of(quote, secondQuote));

        assertThat(quoteService.findAllQuotes())
                .usingRecursiveComparison()
                .isEqualTo(List.of(quote, secondQuote));

        verify(quoteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("should delete quote")
    void deleteById() {
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));

        when(quoteRepository.findAllByAuthorId(1L)).thenReturn(List.of(secondQuote));

        quoteService.deleteById(1L);

        verify(quoteRepository, times(1))
                .deleteById(eq(1L));

        verify(quoteRepository, times(1))
                .findAllByAuthorId(eq(1L));
    }

    @Test
    @DisplayName("should delete quote and remaining orphan author")
    void deleteQuoteByIdAndOrphanAuthor() {
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));

        when(quoteRepository.findAllByAuthorId(1L)).thenReturn(Lists.emptyList());

        quoteService.deleteById(1L);

        verify(quoteRepository, times(1))
                .deleteById(eq(1L));

        verify(quoteRepository, times(1))
                .findAllByAuthorId(eq(1L));

        verify(authorService, times(1))
                .deleteById(eq(1L));
    }

    @Test
    @DisplayName("should throw exception when trying to remove not existing quote")
    void throwExceptionWhileDeleting() {
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));

        doThrow(new EmptyResultDataAccessException(1)).when(quoteRepository).deleteById(1L);

        assertThatThrownBy(() -> quoteService.deleteById(1L))
                .hasMessage("Nie znaleziono cytatu o id = 1")
                .isExactlyInstanceOf(QuoteNotFoundException.class);

        verify(quoteRepository, times(1))
                .deleteById(eq(1L));

        verify(quoteRepository, times(1))
                .findById(eq(1L));
    }
}
